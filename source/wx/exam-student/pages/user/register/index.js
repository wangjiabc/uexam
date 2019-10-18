const app = getApp()
Page({
  data: {
    levelIndex: 0,
    code: ""
  },
  onLoad: function(e) {
    this.getWxCode();
  },
  getWxCode() {
    let _this = this;
    wx.login({
      success(wxres) {
        if (wxres.code) {
          _this.setData({
            code: wxres.code
          })
        }
      }
    })
  },
  bindLevelChange: function(e) {
    this.setData({
      levelIndex: e.detail.value
    })
  },
  formSubmit: function(e) {
    console.log('this', this.data);
    let _this = this;
    let form = e.detail.value
    form.code = this.data.code
    if (form.userName == null || form.userName == '') {
      app.message('用户名不能为空', 'error');
      return;
    }
    if (form.password == null || form.password == '') {
      app.message('密码不能为空', 'error');
      return;
    }
    if (form.duty == null || form.userLevel == '') {
      app.message('职务不能为空', 'error');
      return;
    }
    if (form.duty == null || form.phone == '') {
      app.message('手机号不能为空', 'error');
      return;
    }
    _this.setData({
      spinShow: true
    });
    app.formPost('/api/wx/student/user/register', form)
      .then(res => {
        _this.setData({
          spinShow: false
        });
        if (res.code == 1) {
          wx.reLaunch({
            url: '/pages/user/bind/index',
          });
        } else {
          app.message(res.message, 'error')
        }
      }).catch(e => {
        _this.setData({
          spinShow: false
        });
        app.message(e, 'error')
      })
  }
})