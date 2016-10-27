$(function() {

	$("#robot-submit").click(function(e) {
		var info = $("#robot-info").val();
		var address = $("#robot-address").val();
		if(info == "") {
			alert("请输入内容");
			e.preventDefault();
			return false;
		} else {
			var cur = new Date();
			var y = cur.getFullYear();
			var M = cur.getMonth() + 1;
			var d = cur.getDate();
			var h = cur.getHours();
			var m = cur.getMinutes();
			var s = cur.getSeconds();
			if(m < 10) {
				m = "0" + m;
			}
			if(h < 10) {
				h = "0" + h;
			}
			if(s < 10) {
				s = "0" + s;
			}
			$("#robot-content").append(y + "-" + M + "-" + d + " " + h + ":" + m + ":" + s + " 我：" + info + "<br/>");
			$.ajax({
				url: "/robot/robot.do",
				type: "POST",
				data: {
					info: info,
					address: address
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
					if(m < 10) {
						m = "0" + m;
					}
					if(h < 10) {
						h = "0" + h;
					}
					if(s < 10) {
						s = "0" + s;
					}
					$("#robot-content").append(y + "-" + M + "-" + d + " " + h + ":" + m + ":" + s + " 弱智：" + data.object + "<br/>");
					$("#robot-info").val("");
				},
				fail: function(status) {

					alert("服务器异常");
				}
			});
		}
	})

	window.onload = function() {　　
		var uri = window.location.pathname;
		var search = window.location.search.substr(1);
		var ip = "";
		if(uri.endsWith("/tosay.html") && (search == "" || ip != search)) {
			//			window.location.href = uri;
			var jqxhr = $.getJSON("getIp.do",
				function(data) {
					ip = data.ip;
					if(ip == search)
						return;
					window.location.href = "/qq/tosay.html?" + data.ip;
				})

		}
	}

	window.onload = function getLocation() {
		if(navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showPosition, showError);
		} else {}
	}

	function showPosition(position) {
		$.ajax({
			url: "/address/address.do",
			type: "POST",
			data: {
				latitude: position.coords.latitude,
				longitude: position.coords.longitude
			},
			dataType: "json",
			success: function(data) {
				var address = data.object;
				$("#robot-address").val(address);
			},
			fail: function(status) {

			}
		});
	}

	function showError(error) {
		switch(error.code) {
			case error.PERMISSION_DENIED:
				console.log("User denied the request for Geolocation.")
				break;
			case error.POSITION_UNAVAILABLE:
				console.log("Location information is unavailable.")
				break;
			case error.TIMEOUT:
				console.log("The request to get user location timed out.")
				break;
			case error.UNKNOWN_ERROR:
				console.log("An unknown error occurred.")
				break;
		}
	}
	
	
	
	
	
	
	$("#dialog-close").click(function(e) {
		$("#dialog").hide();
		$("#robot-div").css({"margin-top":"230px"});
	})

});