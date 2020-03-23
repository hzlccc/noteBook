/**
 * 
 */

var layer;
var form;
 
layui.use(['layer', 'form'], function(){
  layer = layui.layer;
  form = layui.form;
  form.render();
  init();
 });

/**
 * 初始化
 */
function init(){
	
	/**
	 * 初始化监听器
	 */
	initMonitors();
	
	//getUserName();
	
}

function initMonitors(){
	
	
	// 校验两次密码是否一致

	form.verify({
		confirmPass:function(value){
			if($('input[name=password]').val() !== value)
				return '两次密码输入不一致！';
			}
	});
	
	form.on('submit(formDemo)', function(data){
		var params = data.field;
		var password = params.password;
		var password2 = params.password2;
		if(password!=password2){
			  layer.msg('两次密码不一致', {
			        time: 20000, //20s后自动关闭
			        btn: ['明白了', '知道了', '哦']
			      });
			
		}else{
			
			$.ajax({
				type: "post",
				contentType : 'application/json;charset=UTF-8',
				dataType: "text",
				data:JSON.stringify(data.field),
				url: 'http://192.168.0.111:9999/user/register',
				success: function (data) {
					layer.msg(data);
				}
				});
		}
		return false;
	  });
}

