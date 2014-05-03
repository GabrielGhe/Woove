/**
 * @author 0737019
 */

var invoiceT;

$(function() {
		
		//dataTable plugin for track table
		invoiceT = $('#tableLoop').dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers",
			"sDom": '<lf<t>ip>', 
			'iDisplayLength': 8
		});
		
		//hide the unwanted features
		$('#tableLoop_length').css("display", "none");
		$('#tableLoop_first').css("display", "none");
		$('#tableLoop_last').css("display", "none");
		
		/* Add a click handler to the rows - this could be used as a callback */
		$("#tableLoop tbody").click(function(event) {
			$(invoiceT.fnSettings().aoData).each(function (){
				$(this.nTr).removeClass('row_selected').css('background-color', 'white');
			});
			$(event.target.parentNode).addClass('row_selected').css('background-color', '#cc89b0');
			anSelected = fnGetSelected( invoiceT );
			
			$('#invoice').val($(anSelected).children().eq(0).text());
			
			$("#manageInvDetail").submit();
		});
	}); 