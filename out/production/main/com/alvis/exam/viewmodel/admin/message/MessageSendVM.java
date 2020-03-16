package com.alvis.exam.viewmodel.admin.message;

import com.alvis.exam.viewmodel.admin.user.UserResponseVM;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class MessageSendVM {

    @NotBlank
    private String title;
    @NotBlank
    private String content;

    @Size(min = 1, message = "接收人不能为空")
    private List<Integer> receiveUserIds;

	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	public String getContent() {
		// TODO Auto-generated method stub
		return content;
	}

	public List<Integer> getReceiveUserIds() {
		// TODO Auto-generated method stub
		return receiveUserIds;
	}
}
