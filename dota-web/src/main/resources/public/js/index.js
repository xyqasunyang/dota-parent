$(function() {

	//消除消息框错误信息
	$(".dropdown-toggle").click(function() {
		$("#account_error").html("");
		$("#password_error").html("");
		$("#error").html("");

		//清除文本框内容
		$("input[id='account']").val("").focus();
		$("input[id='password']").val("").focus();
	})

	$("#index_login").click(function() {
		$("#account_error").html("");
		$("#password_error").html("");

	})

	//登陆
	$("#index_login").click(function() {
		var account = $("#account").val();
		var password = $("#password").val();
		if(account.length < 5) {
			$("#account_error").html("账号不合法");
			return;
		}
		if(password.length < 6) {
			$("#password_error").html("密码不合法");
			return;
		}
		$.ajax({
			url: "login.do",
			type: "POST",
			data: {
				account: account,
				password: password
			},
			dataType: "json",
			success: function(data) {
				//普通用户
				if(data.code == 0) {
					window.location.href = "adminAccount.html";
				} else if(data.code == 1) {
					//管理员
					window.location.href = "adminAccount.html";
				} else if(data.code == 2) {
					window.location.href = "adminAccount.html";
					//教师
					$("#error").html(data.error);

				} else {
					$("#error").html(data.error);
				}

			},
			fail: function(status) {

				alert("服务器异常");
			}
		});

	})

	//注册跳转
	$("#button_register").click(function() {
		window.location.href = "register.html";
	})

	//管理员注册
	$("#admin_register_account").click(function() {
		var uri = window.location.pathname;
		var str = new Array();
		str = uri.split("/");
		var url = str[str.length - 1];
		var identity = 0;
		if(url == "teacherAccount.html") {
			identity = 2;
		} else if(url == "studentAccount.html") {
			identity = 0;
		} else if(url == "adminAccount.html") {
			identity = 1;
		} else {
			identity = 0;
		}
		$("#register_name_error").html("");
		$("#register_phone_error").html("");
		$("#register_account_error").html("");
		$("#register_password_error").html("");
		$(".register_error").html("");
		var name = $("#register_name").val();
		if(name.length < 1) {
			$("#register_name_error").html("请填写姓名");
			return;
		}
		var phone = $("#register_phone").val();
		if(phone.length != 11) {
			$("#register_phone_error").html("请填写正确手机号码");
			return;
		}
		var account = $("#register_account").val();
		if(account.length < 6) {
			$("#register_account_error").html("请填写6位以上账号");
			return;
		}
		var password = $("#register_password").val();
		if(password.length < 6) {
			$("#register_password_error").html("请填写6位以上密码");
			return;
		}
		var description = $("#register_description").val();
		$.ajax({
			url: "register.do",
			type: "POST",
			data: {
				account: account,
				password: password,
				name: name,
				phone: phone,
				description: description,
				identity: identity
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 0) {
					alert("注册成功...");
					window.location.href = url;
				} else {
					$(".register_error").html(data.error);
				}
			},
			fail: function(status) {

				alert("服务器异常");
			}
		});

	})

	//管理员详情
	$(".glyphicon.glyphicon-search.1").click(function(span) {
		var $this = this;
		var id = $(this).attr("data-id");
		$.ajax({
			url: "getAdminAccount.do",
			type: "POST",
			data: {
				userId: id
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 0) {
					$(".user_name").val(data.object.name);
					$(".user_phone").val(data.object.phone);
					$(".user_account").val(data.object.account);
					$(".user_account").attr("disabled", "true");
					$(".user_password").val(data.object.password);
					$(".user_description").val(data.object.description);
					$("#modalId").val(id);
				} else {
					$(".register_error").html(data.error);
				}
			},
			fail: function(status) {

				alert("服务器异常");
			}
		});

	})

	//管理员修改
	$(".modify_user").click(function() {
		var uri = window.location.pathname;
		var str = new Array();
		str = uri.split("/");
		var url = str[str.length - 1];
		var id = $("#modalId").val();
		$(".user_name_error").html("");
		$(".user_phone_error").html("");
		$(".user_account_error").html("");
		$(".user_password_error").html("");
		$(".user_error").html("");
		var name = $(".user_name").val();
		if(name.length < 1) {
			$(".user_name_error").html("请填写姓名");
			return;
		}
		var phone = $(".user_phone").val();
		if(phone.length != 11) {
			$(".user_phone_error").html("请填写正确手机号码");
			return;
		}
		var account = $(".user_account").val();
		if(account.length < 5) {
			$(".user_account_error").html("请填写5位以上账号");
			return;
		}
		var password = $(".user_password").val();
		if(password.length < 6) {
			$(".user_password_error").html("请填写6位以上密码");
			return;
		}
		var description = $(".user_description").val();
		$.ajax({
			url: "modifyAdminAccount.do",
			type: "POST",
			data: {
				password: password,
				name: name,
				phone: phone,
				description: description,
				userId: id
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 0) {
					alert("修改成功...");
					window.location.href = url;
				} else {
					$(".register_error").html(data.error);
				}
			},
			fail: function(status) {

				alert("服务器异常");
			}
		});

	})

	//管理员删除
	$("#deleteUser").click(function() {
		if(confirm('确认删除！ ') == false) return false;
		var id = $("#modalId").val();
		var uri = window.location.pathname;
		var str = new Array();
		str = uri.split("/");
		var url = str[str.length - 1];
		$.ajax({
			url: "modifyAdminAccount.do",
			type: "POST",
			data: {
				userId: id,
				isDelete: 1
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 0) {
					alert("删除成功...");
					window.location.href = url;
				} else {
					alert("删除失败...");
				}
			},
			fail: function(status) {

				alert("服务器异常");
			}
		});

	})

	//推荐
	$("#user-push").click(function() {
		if(confirm('确认推荐！ ') == false) return false;
		var id = $("#modalId").val();
		var uri = window.location.pathname;
		var str = new Array();
		str = uri.split("/");
		var url = str[str.length - 1];
		$.ajax({
			url: "modifyAdminAccount.do",
			type: "POST",
			data: {
				userId: id,
				isPush: 1
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 0) {
					alert("推荐成功...");
					window.location.href = url;
				} else {
					alert("推荐失败...");
				}
			},
			fail: function(status) {

				alert("服务器异常");
			}
		});

	})

	//教师注册
	$("#teacher_register_account").click(function() {
		$("#register_name_error").html("");
		$("#register_phone_error").html("");
		$("#register_account_error").html("");
		$("#register_password_error").html("");
		$(".register_error").html("");
		var name = $("#register_name").val();
		if(name.length < 1) {
			$("#register_name_error").html("请填写姓名");
			return;
		}
		var phone = $("#register_phone").val();
		if(phone.length != 11) {
			$("#register_phone_error").html("请填写正确手机号码");
			return;
		}
		var account = $("#register_account").val();
		if(account.length < 6) {
			$("#register_account_error").html("请填写6位以上账号");
			return;
		}
		var password = $("#register_password").val();
		if(password.length < 6) {
			$("#register_password_error").html("请填写6位以上密码");
			return;
		}
		var description = $("#register_description").val();
		$.ajax({
			url: "register.do",
			type: "POST",
			data: {
				account: account,
				password: password,
				name: name,
				phone: phone,
				description: description,
				identity: 2
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 0) {
					alert("注册成功...");
					window.location.href = "adminAccount.html";
				} else {
					$(".register_error").html(data.error);
				}
			},
			fail: function(status) {

				alert("服务器异常");
			}
		});

	})

	//图片详情
	$(".glyphicon.glyphicon-edit.2").click(function(span) {
		var $this = this;
		var id = $(this).attr("data-id");
		$("#imgId").val(id);
	})

	//查看图片
	$(".glyphicon.glyphicon-picture.3").click(function(span) {
		var $this = this;
		var id = $(this).attr("data-id");
		$.ajax({
			url: "getAdminAccount.do",
			type: "post",
			data: {
				userId: id
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 0) {
					$("#seachImg").attr("src", "../../../resources/picture/" + data.object.img);
				} else {
					alert("查看图片失败");
				}
			},
			fail: function(status) {

				alert("服务器异常");
			}
		});
	})

	//上传图片
	$("#imgupload").click(function() {
		var uri = window.location.pathname;
		var str = new Array();
		str = uri.split("/");
		var url = str[str.length - 1];
		var id = $("#imgId").val();
		var form = new FormData(document.getElementById("form-imgupload"));
		$.ajax({
			url: "getImg.do",
			type: "post",
			data: form,
			processData: false,
			contentType: false,
			dataType: "json",
			success: function(data) {
				if(data.code == 0) {
					alert("上传成功");
					window.location.href = url;
				} else {
					alert("上传失败");
				}
			},
			fail: function(status) {

				alert("服务器异常");
			}
		});

	})

	//课程注册
	$("#createClass").click(function() {
		$("#class_name_error").html("");
		$("#class_class_error").html("");
		$("#class_hour_error").html("");
		$("#class_oneClassHour_error").html("");
		$("#class_teacherId_error").html("");
		$("#class_price_error").html("");
		$("#class_peopleNumber_error").html("");
		$("#class_description_error").html("");
		var userId = $("#class_teacherId option:selected").attr("data-id");
		var name = $("#class_name").val();
		var className = $("#class_class").val();
		var classHour = $("#class_hour").val();
		var oneClassHour = $("#class_oneClassHour").val();
		var price = $("#class_price").val();
		var description = $("#class_description").val();
		var peopleNumber = $("#class_peopleNumber").val();
		$.ajax({
			url: "createClass.do",
			type: "POST",
			data: {
				name: name,
				className: className,
				classHour: classHour,
				oneClassHour: oneClassHour,
				price: price,
				peopleNumber: peopleNumber,
				description: description,
				userId: userId
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 0) {
					alert("注册成功...");
					window.location.href = "classManage.html";
				} else {
					$(".class_error").html(data.error);
				}
			},
			fail: function(status) {

				alert("服务器异常");
			}
		});

	})

	//课程详情
	$(".glyphicon.glyphicon-search.1").click(function(span) {
		var $this = this;
		var id = $(this).attr("data-id");
		$.ajax({
			url: "getClass.do",
			type: "POST",
			data: {
				curriculumId: id
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 0) {
					$(".class_name").val(data.object.name);
					$(".class_class").val(data.object.className);
					$(".class_hour").val(data.object.classHour);
					$(".class_oneClassHour").val(data.object.oneClassHour);
					$("#class_teacherIds").val(data.object.teacherName);
					$(".class_price").val(data.object.price);
					$(".class_description").val(data.object.description);
					$(".class_peopleNumber").val(data.object.peopleNumber);
					$("#classId").val(id);
				} else {
					$(".class_error").html(data.error);
				}
			},
			fail: function(status) {

				alert("服务器异常");
			}
		});

	})

	//课程修改
	$("#modifyClass").click(function() {
		var id = $("#classId").val();
		var uri = window.location.pathname;
		var str = new Array();
		str = uri.split("/");
		var url = str[str.length - 1];
		var name = $(".class_name").val();
		var className = $(".class_class").val();
		var classHour = $(".class_hour").val();
		var oneClassHour = $(".class_oneClassHour").val();
		var userId = $("#class_teacherIds option:selected").attr("data-id");
		var price = $(".class_price").val();
		var description = $(".class_description").val();
		var peopleNumber = $(".class_peopleNumber").val();
		$.ajax({
			url: "modifyClass.do",
			type: "POST",
			data: {
				name: name,
				className: className,
				classHour: classHour,
				oneClassHour: oneClassHour,
				userId: userId,
				price: price,
				peopleNumber: peopleNumber,
				description: description,
				curriculumId: id
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 0) {
					alert("修改成功...");
					window.location.href = url;
				} else {
					$(".class_error").html(data.error);
				}
			},
			fail: function(status) {

				alert("服务器异常");
			}
		});

	})

	//课程删除
	$("#deleteClass").click(function() {
		if(confirm('确认删除！ ') == false) return false;
		var id = $("#classId").val();
		var uri = window.location.pathname;
		var str = new Array();
		str = uri.split("/");
		var url = str[str.length - 1];
		$.ajax({
			url: "modifyClass.do",
			type: "POST",
			data: {
				curriculumId: id,
				isDelete: 1
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 0) {
					alert("删除成功...");
					window.location.href = url;
				} else {
					alert("删除失败...");
				}
			},
			fail: function(status) {

				alert("服务器异常");
			}
		});

	})

	//推荐课程
	$("#pushClass").click(function() {
		if(confirm('确认推荐！ ') == false) return false;
		var id = $("#classId").val();
		var uri = window.location.pathname;
		var str = new Array();
		str = uri.split("/");
		var url = str[str.length - 1];
		$.ajax({
			url: "modifyClass.do",
			type: "POST",
			data: {
				curriculumId: id,
				isRecommend: 1
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 0) {
					alert("推荐成功...");
					window.location.href = url;
				} else {
					alert("推荐失败...");
				}
			},
			fail: function(status) {

				alert("服务器异常");
			}
		});

	})

	//学生课程注册
	$("#create-personClass").click(function() {
		var studentName = $("#personClass-studentId option:selected").val();
		var studentId = $("#personClass-studentId option:selected").attr("data-id");
		var className = $("#personClass-curriculumId option:selected").val();
		var curriculumId = $("#personClass-curriculumId option:selected").attr("data-id");
		var teacherName = $("#personClass-teacherId option:selected").val();
		var teacherId = $("#personClass-teacherId option:selected").attr("data-id");
		$.ajax({
			url: "createPersonClass.do",
			type: "POST",
			data: {
				studentName: studentName,
				studentId: studentId,
				className: className,
				curriculumId: curriculumId,
				teacherName: teacherName,
				teacherId: teacherId
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 0) {
					alert("注册成功...");
					window.location.href = "studentClass.html";
				} else {
					$(".class_error").html(data.error);
				}
			},
			fail: function(status) {

				alert("服务器异常");
			}
		});

	})

	//学生课程删除
	$("#studentClass-delete").click(function() {
		if(confirm('确认删除！ ') == false) return false;
		var personClassId = $("#studentClass-delete").attr("data-id");
		var uri = window.location.pathname;
		var str = new Array();
		str = uri.split("/");
		var url = str[str.length - 1];
		$.ajax({
			url: "modifyStudentClass.do",
			type: "POST",
			data: {
				personClassId: personClassId,
				isDelete: 1
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 0) {
					alert("删除成功...");
					window.location.href = url;
				} else {
					alert("删除失败...");
				}
			},
			fail: function(status) {

				alert("服务器异常");
			}
		});

	})

	//学生课程搜索
	$("#studentClass-search").click(function() {
		var studentName = $("#studentClass-studentName").val();
		window.location.href = "studentClass.html?queryText=" + studentName;
		/*var uri = window.location.pathname;
		var str = new Array();
		str = uri.split("/");
		var url = str[str.length - 1];
		$.ajax({
			url: "studentClass.html",
			type: "POST",
			data: {
				queryText: studentName
			},
			dataType: "html",
			success: function(data) {

				$("body").html(data);
				$("#studentClass-studentName").attr("value", studentName);

			},
			fail: function(status) {

				alert("服务器异常");
			}
		});*/

	})

	//学生搜索
	$("#studentAccount-search").click(function() {
		var name = $("#studentAccount-studentName").val();
		window.location.href = "studentAccount.html?queryText=" + name;
		/*		var uri = window.location.pathname;
				var str = new Array();
				str = uri.split("/");
				var url = str[str.length - 1];
				$.ajax({
					url: "studentAccount.html",
					type: "POST",
					data: {
						queryText: name
					},
					dataType: "html",
					success: function(data) {

						$("body").html(data);
						$("#studentAccount-studentName").attr("value", name);

					},
					fail: function(status) {

						alert("服务器异常");
					}
				});*/

	})

	//教师搜索
	$("#teacherAccount-search").click(function() {
		var name = $("#teacherAccount-studentName").val();
		window.location.href = "teacherAccount.html?queryText=" + name;
		/*var uri = window.location.pathname;
		var str = new Array();
		str = uri.split("/");
		var url = str[str.length - 1];
		$.ajax({
			url: "teacherAccount.html",
			type: "POST",
			data: {
				queryText: name
			},
			dataType: "html",
			success: function(data) {

				$("body").html(data);
				$("#teacherAccount-studentName").attr("value", name);

			},
			fail: function(status) {

				alert("服务器异常");
			}
		});*/

	})

	//管理员搜索
	$("#adminAccount-search").click(function() {
		var name = $("#adminAccount-studentName").val();
		window.location.href = "adminAccount.html?queryText=" + name;
		/*		var uri = window.location.pathname;
				var str = new Array();
				str = uri.split("/");
				var url = str[str.length - 1];
				$.ajax({
					url: "adminAccount.html",
					type: "POST",
					data: {
						queryText: name
					},
					dataType: "html",
					success: function(data) {

						$("html").html(data);
						$("#adminAccount-studentName").attr("value", name);

					},
					fail: function(status) {

						alert("服务器异常");
					}
				});*/

	})

	/*window.onload = function() {　　
		var width = $(".head-background").width();
		var img = new Image();
		img.src = "../../resources/picture/test11.jpg";
		var coefficient = width / img.width;
		$(".head-background").height(img.height * coefficient);
		if (width < 768) {
			$("#head-nav-two").css("display", "none");
			$("#head-nav-one").append("<li><a href='teacherIntroduce.html' style='color: white; '><span class='glyphicon glyphicon-globe' style='padding-top: 3px;'></span>&nbsp;教师介绍</a></li>");
			$("#head-nav-one").append("<li><a href='studentIntroduce.html' style='color: white; '><span class='glyphicon glyphicon-globe' style='padding-top: 3px;'></span>&nbsp;学员介绍</a></li>");
			$("#head-nav-one").append("<li><a href='classIntroduce.html' style='color: white; '><span class='glyphicon glyphicon-globe' style='padding-top: 3px;'></span>&nbsp;课程介绍</a></li>");
			$("#head-nav-one").append("<li><a href='#' style='color: white; '><span class='glyphicon glyphicon-globe' style='padding-top: 3px;'></span>&nbsp;关于我们</a></li>");
			$(".head-background").height("180");
			img.src = "../../resources/picture/index1.jpg";
			$("#index-video").css("display", "none");
		}
	}*/

	//课程搜索
	$("#qq-submit").click(function(e) {
		var qq = $("#qq-number").val();
		if(qq == "") {
			alert("请输入QQ号");
			e.preventDefault();
			return false;
		} else {
			e.submit();
		}
	})

	$("#robot-submit").click(function(e) {
		var info = $("#robot-info").val();
		if(info == "") {
			alert("请输入内容");
			e.preventDefault();
			return false;
		} else {
			$.ajax({
				url: "/robot/robot.do",
				type: "POST",
				data: {
					info: info
				},
				dataType: "json",
				success: function(data) {
					var cur = new Date();
					var y = cur.getFullYear();
					var M = cur.getMonth() + 1;
					var d = cur.getDate();
					var h = cur.getHours();
					var m = cur.getMinutes();
					var s = cur.getSeconds();
					$("#robot-content").append(y+"-"+M+"-"+d+" "+h+":"+m+":"+s + " 我：" + info + "<br/>");
					$("#robot-content").append(y+"-"+M+"-"+d+" "+h+":"+m+":"+s + " 机器人："+data.object + "<br/>");

				},
				fail: function(status) {

					alert("服务器异常");
				}
			});
		}
	})

});