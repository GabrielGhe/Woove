/**
 * @author 0737019
 */

var cilentT;
 var formAction; 
  
	$(function() {
		
		//dataTable plugin for track table
		cilentT = $('#tableLoop').dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers",
			"sDom": '<lf<t>ip>', 
			'iDisplayLength': 8
		});
		
		formAction = $('#clientForm').attr('action'); 
		
		/* Add a click handler to the rows - this could be used as a callback */
		$("#tableLoop tbody").click(function(event) {
			$(cilentT.fnSettings().aoData).each(function (){
				$(this.nTr).removeClass('row_selected').css('background-color', 'white');
			});
			$(event.target.parentNode).addClass('row_selected').css('background-color', '#cc89b0');
			anSelected = fnGetSelected( cilentT ); 
			
			//puts the values from the table into the form
			$('#fid').val($(anSelected).children().eq(0).text());
  			$('#ftitle').val($(anSelected).children().eq(1).text());
  			$('#fln').val($(anSelected).children().eq(2).text());
  			$('#ffn').val($(anSelected).children().eq(3).text());
  			$('#fcn').val($(anSelected).children().eq(4).text());
			$('#fadd').val($(anSelected).children().eq(5).text());
			$('#fadd2').val($(anSelected).children().eq(6).text());
			$('#fcity').val($(anSelected).children().eq(7).text());
			$('#fprov').val($(anSelected).children().eq(8).text());
			$('#fcountry').val($(anSelected).children().eq(9).text());
			$('#fpc').val($(anSelected).children().eq(10).text());
			$('#fhp').val($(anSelected).children().eq(11).text());
			$('#fcp').val($(anSelected).children().eq(12).text());
			$('#fpwd').val($(anSelected).children().eq(13).text()); 
			$('#femail').val($(anSelected).children().eq(14).text());
			$('#fsales').val($(anSelected).children().eq(15).text());
			 
			//Puts the values from the table into the validator form 
			$('#hfid').val($(anSelected).children().eq(0).text());
  			$('#hftitle').val($(anSelected).children().eq(1).text()); 
  			$('#hfln').val($(anSelected).children().eq(2).text());
  			$('#hffn').val($(anSelected).children().eq(3).text());
  			$('#hfcn').val($(anSelected).children().eq(4).text());
			$('#hfadd').val($(anSelected).children().eq(5).text());
			$('#hfadd2').val($(anSelected).children().eq(6).text());
			$('#hfcity').val($(anSelected).children().eq(7).text());
			$('#hfprov').val($(anSelected).children().eq(8).text());
			$('#hfcountry').val($(anSelected).children().eq(9).text());
			$('#hfpc').val($(anSelected).children().eq(10).text());
			$('#hfhp').val($(anSelected).children().eq(11).text());
			$('#hfcp').val($(anSelected).children().eq(12).text());
			$('#hfpwd').val($(anSelected).children().eq(13).text());
			$('#hfemail').val($(anSelected).children().eq(14).text());
			$('#hfsales').val($(anSelected).children().eq(15).text());
			
			$('#gMiddleDivClient').gMiddleDiv({cancelDiv:'middleDivCancel' });
			
		});	
		
		//if the value was changed
		function checkIfChanged(){
			if(
	  			$('#ftitle').val() != $('#hftitle').val() || 
	  			$('#fln').val() != $('#hfln').val() ||
	  			$('#ffn').val() != $('#hffn').val() || 
	  			$('#fcn').val() != $('#hfcn').val() || 
				$('#fadd').val() != $('#hfadd').val() ||
				$('#fadd2').val() != $('#hfadd2').val() ||
				$('#fcity').val() != $('#hfcity').val() ||
				$('#fprov').val() != $('#hfprov').val() ||
				$('#fcountry').val() != $('#hfcountry').val() ||
				$('#fpc').val() != $('#hfpc').val() || 
				$('#fhp').val() != $('#hfhp').val() ||
				$('#fcp').val() != $('#hfcp').val() ||
				$('#fpwd').val() != $('#hfpwd').val() ||
				$('#femail').val() != $('#hfemail').val()
			){
				return true;
			}else{
				return false;
			}
		}
		
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
		
		$('#gMiddleDivClient').hide();
		
		
		$('#Delete').click(function(){
			$('#clientForm').attr('action', formAction + "_d");  
			$('#clientForm').submit();
		});
		$('#Submit').click(function(){
			if(checkIfChanged()){ 
				if($('#fid').val() < "0"){
					$('#clientForm').attr('action', formAction + "_a"); 
					$('#clientForm').submit();
				}
				else{	
					$('#clientForm').attr('action', formAction + "_u"); 
					$('#clientForm').submit();
				}
			}
		});
		$('#New').click(function(){
			$('#clientForm')[0].reset();
			$('#fid').val("-5"); 
		});
		$('#middleDivCancel').click(function(){ 
			$('#clientForm').attr('action', formAction); 
		});
	});