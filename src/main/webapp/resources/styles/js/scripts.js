
jQuery(document).ready(function() {
	
	var flag = true;	//用于判断账号是否存在
    /*
        Form
    */
    $('.registration-form fieldset:first-child').fadeIn('slow');
    
    $('.registration-form input[type="text"], .registration-form input[type="password"], .registration-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $("#form-first-name").blur(function(){
    	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    	if ($("#form-first-name").val() != ""){
    		 if(!filter.test($("#form-first-name").val())){
        		 alert('您的账号格式不正确，请填写正确的邮箱地址！');
        		 flag = false;
        		 $("#form-first-name").addClass('input-error');
        	}
        	else{
        		$.ajax({
        			type: 'get',
        			url:'checkUser?userAccount=' + $("#form-first-name").val(),
        			dataType:'text',
        			success:function(data){
        				if(data == "exist"){
        		    		alert('该账号已经存在，请重新输入账号！');
        		    		flag = false;
        		    		$("#form-first-name").addClass('input-error');
        				}
        				else{
        					flag = true;
        				}
        			},
        			error:function(data){
        			}
        		});
        	}
    	}
    	else{
    		flag = true;
    	}
    	
    });
       
    // next step
    $('.registration-form .btn-next').on('click', function() {
    	var parent_fieldset = $(this).parents('fieldset');
    	var next_step = true;
    	
    	parent_fieldset.find('input[type="text"], input[type="password"], textarea').each(function() {
    		if( $(this).val() == "" ) {
    			$(this).addClass('input-error');
    			next_step = false;
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
 	
    	
    	
        if( $("#form-password").val() != $("#form-repeat-password").val() ) {
            $(this).addClass('input-error');
            next_step = false;
        }
        
        if(flag == false){
        	$("#form-first-name").addClass('input-error');
        }
        else{
        	$("#form-first-name").removeClass('input-error');
        }
    		
    	if( next_step && flag) {
    		parent_fieldset.fadeOut(400, function() {
	    		$(this).next().fadeIn();
	    	});
    	}
    	
    });
    
    // previous step
    $('.registration-form .btn-previous').on('click', function() {
    	$(this).parents('fieldset').fadeOut(400, function() {
    		$(this).prev().fadeIn();
    	});
    });
    
    // submit
    $('.registration-form').on('submit', function(e) {
    	
    	if($("#form-facebook").val() != "" && !(/^1[34578]\d{9}$/.test($("#form-facebook").val()))){ 
            alert("您的手机号码有误，请重填");
            e.preventDefault();
			$(this).addClass('input-error');
        }
    	
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function() {
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    });
    
    
});
