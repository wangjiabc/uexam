const app = getApp()
Page({
  data: {
    spinShow: false,
    userName: '',
    password: '',
  },
  onLoad: function(options) {
    //进入小程序直接进行登录
    console.log('onload', options);
    wx.login({
      success(wxres) {
        if (wxres.code) {
          console.log('code', wxres.code);
          app.formPost('/api/wx/student/auth/bind', {
              code: wxres.code
            })
            .then(res => {
              console.log('token',res.response);
              //注册页面跳转
              if (res.code == 5) {
                wx.navigateTo({
                  url: "../register/index"
                })
              } else if (res.code == 1) {
                wx.setStorageSync('token', res.response)
                wx.reLaunch({
                  url: '/pages/index/index',
                });
              }
            }).catch(e => {
              app.message(e, 'error')
            })
        } else {
          app.message(res.errMsg, 'error')
        }
      }
    })
  },
  formSubmit: function(e) {
    let _this = this
    _this.setData({
      spinShow: true
    });
    wx.login({
      success(wxres) {
        if (wxres.code) {
          e.detail.value.code = wxres.code
          console.log(e.detail);
          app.formPost('/api/wx/student/auth/bind', e.detail.value)
            .then(res => {
              _this.setData({
                spinShow: false
              });
              if (res.code == 1) {
                wx.setStorageSync('token', res.response)
                wx.reLaunch({
                  url: '/pages/index/index',
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
        } else {
          app.message(res.errMsg, 'error')
        }
      }
    })
  },
  register: function(e) {
    wx.navigateTo({
      url: "../register/index"
    })
  }
})