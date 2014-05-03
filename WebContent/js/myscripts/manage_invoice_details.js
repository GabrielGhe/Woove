/**
 * @author 0737019
 */

$(function() {
	$(".remove").click(function(){
		$("#removeForm").val($(this).attr("id")); 
		if($("#removeForm").val() != null){
			$("#invoiceForm").submit(); 
		}
	}); 
}); 