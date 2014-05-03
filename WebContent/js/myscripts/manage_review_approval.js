/**
 * @author 0737019
 */

var formAction;

$(function() {
		//dataTable plugin for track table
		trackT = $('#tableLoop').dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers",
			"sDom": '<lf<t>ip>',
			'iDisplayLength': 8
		}); 
		
		formAction = $('#myForm').attr('action');
		
		/* Add a click handler to the rows - this could be used as a callback */
		$("#tableLoop tbody").click(function(event) {
			$(trackT.fnSettings().aoData).each(function (){
				$(this.nTr).removeClass('row_selected').css('background-color', 'white');
			});
			$(event.target.parentNode).addClass('row_selected').css('background-color', '#cc89b0');
			var anSelected = fnGetSelected( trackT );

			//puts the values from the table into the form
			$('#review_id').val($(anSelected).children().eq(0).text());
		});
		
		$('#approve').click(function(){
			$('#myForm').attr('action', formAction + "_approve");
			if($('#review_id').val() != "")
				$('#myForm').submit();
		});
		$('#reject').click(function(){
			$('#myForm').attr('action', formAction + "_reject");
			if($('#review_id').val() != "")
				$('#myForm').submit();
		});
		 
		
		/* Get the rows which are currently selected */
		function fnGetSelected( oTableLocal )
		{
			var aReturn = new Array();
			var aTrs = oTableLocal.fnGetNodes();
			
			for ( var i=0 ; i<aTrs.length ; i++ )
			{
				if ( $(aTrs[i]).hasClass('row_selected') )
				{
					aReturn.push( aTrs[i] );
				}
			}
			return aReturn;
		}	
		
		//hide the unwanted features
		$('#tableLoop_length').css("display", "none");
		$('#tableLoop_first').css("display", "none");
		$('#tableLoop_last').css("display", "none");
	}); 