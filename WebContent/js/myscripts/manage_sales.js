/**
 * @author 0737019
 */

var albumT;
 var trackT;
 var formAction; 
  
	$(function() {
		//dataTable plugin for track table
		trackT = $('#tableLoop').dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers",
			"sDom": '<lf<t>ip>',
			'iDisplayLength': 8
		}); 
		
		formAction = $('#trackForm').attr('action');
		/* Add a click handler to the rows - this could be used as a callback */
		$("#tableLoop tbody").click(function(event) {
			$(trackT.fnSettings().aoData).each(function (){
				$(this.nTr).removeClass('row_selected').css('background-color', 'white');
			});
			$(event.target.parentNode).addClass('row_selected').css('background-color', '#cc89b0');
			var anSelected = fnGetSelected( trackT );

			//puts the values from the table into the form
			$('#fti').val($(anSelected).children().eq(0).text());
			$('#ftai').val($(anSelected).children().eq(1).text());
			$('#fttt').val($(anSelected).children().eq(2).text());
			$('#fta').val($(anSelected).children().eq(3).text());
			$('#ftcp').val($(anSelected).children().eq(4).text());
			$('#ftlp').val($(anSelected).children().eq(5).text());
			$('#ftsp').val($(anSelected).children().eq(6).text());
			$('#fttsp').val($(anSelected).children().eq(7).text());
			
			//puts the values from the table into the validator form 
			$('#hfttsp').val($(anSelected).children().eq(2).text());
			
			$('#gMiddleDivTrack').gMiddleDiv({cancelDiv : 'middleDivCancel'});
			
			//click events for tracks ------------------------------------
			$('#SubmitT').click(function(){ 
				$('#trackForm').attr('action', formAction + "_t"); 
				$('#trackForm').submit();
			});
			$('#middleDivCancel').click(function(){
				$('#trackForm').attr('action', formAction); 
			});
		});
		
		
		//dataTable plugin for album table
		albumT = $('#tableLoopA').dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers",
			"sDom": '<lf<t>ip>',
			'iDisplayLength': 8
		});
		
		
		/* Add a click handler to the rows - this could be used as a callback */
		$("#tableLoopA tbody").click(function(event) {
			$(albumT.fnSettings().aoData).each(function (){
				$(this.nTr).removeClass('row_selected').css('background-color', 'white');
			});
			$(event.target.parentNode).addClass('row_selected').css('background-color', '#cc89b0');
			var anSelected = fnGetSelected( albumT );
			var idSelected = $(anSelected).children().eq(0).text();
			
			//puts the values from the table into the form
			$('#fai').val($(anSelected).children().eq(0).text()); 
			$('#faat').val($(anSelected).children().eq(1).text()); 
			$('#faa').val($(anSelected).children().eq(2).text()); 
			$('#fat').val($(anSelected).children().eq(3).text());  
			$('#facp').val($(anSelected).children().eq(4).text()); 
			$('#falp').val($(anSelected).children().eq(5).text()); 
			$('#fasp').val($(anSelected).children().eq(6).text()); 
			
			//puts the values from the table into the validator form
			$('#hfasp').val($(anSelected).children().eq(9).text());
			
			$('#gMiddleDivAlbum').gMiddleDiv({cancelDiv : 'middleDivCancelA'}); 
			
			//click events for albums-------------------------------------
			$('#SubmitA').click(function(){ 
				$('#albumForm').attr('action', formAction + "_a"); 
				$('#albumForm').submit();
			});
			$('#middleDivCancelA').click(function(){ 
				$('#albumForm').attr('action', formAction); 
			});
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
		
		
		//hide the unwanted features
		$('#tableLoopA_length').css("display", "none");
		$('#tableLoopA_first').css("display", "none");
		$('#tableLoopA_last').css("display", "none");
		
		
		//hides one table and displays the other
		var rowCountT = $('#tableLoop tr').length;
		var rowCountA = $('#tableLoopA tr').length;
		if(rowCountT == 0){	
			$('#tracksContainer').hide();
		}else if(rowCountA == 0){
			$('#albumsContainer').hide();
		}else{
			$('#albumsContainer').hide();
		}
		
		$('#showTracks').click(displayTracks);
		$('#showAlbums').click(displayAlbums);
		
		$('#gMiddleDivTrack').hide();
		$('#gMiddleDivAlbum').hide();
		
	});
	
	
	function displayTracks(){
		$('#albumsContainer').hide();
		$('#tracksContainer').show();
		$('#showTracks').css('background-image', 'url(images/categoryPressed.png)');
		$('#showAlbums').css('background-color', 'white');
		$('#showAlbums').css('background-image', 'none');
	}
	
	function displayAlbums(){
		$('#albumsContainer').show();
		$('#tracksContainer').hide();
		$('#showAlbums').css('background-image', 'url(images/categoryPressed.png)');
		$('#showTracks').css('background-color', 'white');
		$('#showTracks').css('background-image', 'none');
	}