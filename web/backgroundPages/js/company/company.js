
$(document).ready(function(){
	//初始化界面
	
	
	$("#eidtBtn").click(function(){
          window.location.href=MyUtil.getContext()+"backgroundPages/jsp/company/editCompany.jsp";
	});
	
	$("#saveBtn").click(function(){
		$.post('updateCompanyBaseInfo.do',
				{
			    'company_id':$("#cid").val(),
				'company_name':$('#cname').val(),
				'company_enname':$('#cenname').val(),
				'company_tel':$('#ctel').val(),
				'company_fax':$('#cfax').val(),
				'company_qq':$('#cqq').val(),
				'company_email':$('#cemail').val(),
				'company_address':$('#cadd').val(),
				'company_yuming':$('#cym').val(),
				'company_contactuser1':$('#ccontact1').val(),
				'company_phone1':$('#cphone1').val(),
				'company_qq1':$('#cqq1').val(),
				'company_contactuser2':$('#ccontact2').val(),
				'company_phone2':$('#cphone2').val(),
				'company_qq2':$('#cqq2').val()
				},function(result){
					var ret=eval('('+result+')');
					if(ret.success){
						alert('保存成功！');
						 window.location.href=MyUtil.getContext()+"backgroundPages/jsp/company/company.jsp";
					}else{
						alert('保存失败！');
					}
				});
	});
	
});