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
			$('#fts').val($(anSelected).children().eq(4).text());
			$('#ftl').val($(anSelected).children().eq(5).text());
			$('#fttn').val($(anSelected).children().eq(6).text());
			$('#ftg').val($(anSelected).children().eq(7).text());
			$('#ftac').val($(anSelected).children().eq(8).text());
			$('#ftcp').val($(anSelected).children().eq(9).text());
			$('#ftlp').val($(anSelected).children().eq(10).text());
			$('#ftsp').val($(anSelected).children().eq(11).text());
			$('#fttsp').val($(anSelected).children().eq(12).text());
			$('#ftde').val($(anSelected).children().eq(13).text());
			$('#ftr').val($(anSelected).children().eq(14).text());
			
			//puts the values from the table into the validator form 
			$('#hftai').val($(anSelected).children().eq(1).text());
			$('#hfttt').val($(anSelected).children().eq(2).text());
			$('#hfta').val($(anSelected).children().eq(3).text());
			$('#hfts').val($(anSelected).children().eq(4).text());
			$('#hftl').val($(anSelected).children().eq(5).text());
			$('#hfttn').val($(anSelected).children().eq(6).text());
			$('#hftg').val($(anSelected).children().eq(7).text());
			$('#hftac').val($(anSelected).children().eq(8).text());
			$('#hftcp').val($(anSelected).children().eq(9).text());
			$('#hftlp').val($(anSelected).children().eq(10).text());
			$('#hftsp').val($(anSelected).children().eq(11).text());
			$('#hfttsp').val($(anSelected).children().eq(12).text());
			$('#hftde').val($(anSelected).children().eq(13).text());
			$('#hftr').val($(anSelected).children().eq(14).text()); 
			
			$('#gMiddleDivTrack').gMiddleDiv();
			
			//click events for tracks -------------------------------------
			$('#DeleteT').click(function(){
				$('#trackForm').attr('action', formAction + "_td");  
				$('#trackForm').submit();
			});
			$('#SubmitT').click(function(){ 
				if(checkIfChangedTrack()){
					$('#trackForm').attr('action', formAction + "_tu"); 
					$('#trackForm').submit();
				}
			});
			$('#NewT').click(function(){
				$('#trackForm')[0].reset();
				$('#fti').val("-5"); 
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
			$('#fare').val($(anSelected).children().eq(2).text()); 
			$('#faa').val($(anSelected).children().eq(3).text()); 
			$('#fal').val($(anSelected).children().eq(4).text()); 
			$('#fat').val($(anSelected).children().eq(5).text()); 
			$('#fade').val($(anSelected).children().eq(6).text()); 
			$('#facp').val($(anSelected).children().eq(7).text()); 
			$('#falp').val($(anSelected).children().eq(8).text()); 
			$('#fasp').val($(anSelected).children().eq(9).text());
			$('#fatsp').val($(anSelected).children().eq(10).text());
			$('#faimg').val($(anSelected).children().eq(11).text());
			$('#fagre').val($(anSelected).children().eq(12).text()); 
			$('#far').val($(anSelected).children().eq(13).text());
			/*
			alert("0: " +$(anSelected).children().eq(0).text());
			alert("1: " +$(anSelected).children().eq(1).text());
			alert("2: " +$(anSelected).children().eq(2).text());
			alert("3: " +$(anSelected).children().eq(3).text());
			alert("4: " +$(anSelected).children().eq(4).text());
			alert("5: " +$(anSelected).children().eq(5).text());
			alert("6: " +$(anSelected).children().eq(6).text());
			alert("7: " +$(anSelected).children().eq(7).text());
			alert("8: " +$(anSelected).children().eq(8).text());
			alert("9: " +$(anSelected).children().eq(9).text());
			alert("10: " +$(anSelected).children().eq(10).text());
			alert("11: " +$(anSelected).children().eq(11).text());
			alert("12: " +$(anSelected).children().eq(12).text());
			*/
			//puts the values from the table into the validator form
			$('#hfaat').val($(anSelected).children().eq(1).text()); 
			$('#hfare').val($(anSelected).children().eq(2).text()); 
			$('#hfaa').val($(anSelected).children().eq(3).text()); 
			$('#hfal').val($(anSelected).children().eq(4).text()); 
			$('#hfat').val($(anSelected).children().eq(5).text()); 
			$('#hfade').val($(anSelected).children().eq(6).text()); 
			$('#hfacp').val($(anSelected).children().eq(7).text()); 
			$('#hfalp').val($(anSelected).children().eq(8).text()); 
			$('#hfasp').val($(anSelected).children().eq(9).text());
			$('#hfatsp').val($(anSelected).children().eq(10).text());
			$('#hfaimg').val($(anSelected).children().eq(11).text());
			$('#hfagre').val($(anSelected).children().eq(12).text());
			$('#hfar').val($(anSelected).children().eq(13).text());
			
			$('#gMiddleDivAlbum').gMiddleDiv({cancelDiv : 'middleDivCancelA'}); 
			
			//click events for albums-------------------------------------
			$('#DeleteA').click(function(){
				$('#albumForm').attr('action', formAction + "_ad");  
				$('#albumForm').submit();
			});
			$('#SubmitA').click(function(){ 
				if(checkIfChangedAlbum()){ 
					$('#albumForm').attr('action', formAction + "_au"); 
					$('#albumForm').submit();
				}
			});
			$('#NewA').click(function(){
				$('#albumForm')[0].reset();
				$('#fai').val("-5"); 
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
	

	//if the value was changed for tracks 
	function checkIfChangedTrack(){
		if(
  			$('#ftai').val() != $('#hftai').val() || 
  			$('#fttt').val() != $('#hfttt').val() || 
			$('#fta').val() != $('#hfta').val() ||
			$('#fts').val() != $('#hfts').val() ||
			$('#ftl').val() != $('#hftl').val() ||
			$('#fttn').val() != $('#hfttn').val() ||
			$('#ftg').val() != $('#hftg').val() ||
			$('#ftac').val() != $('#hftac').val() || 
			$('#ftcp').val() != $('#hftcp').val() ||
			$('#ftlp').val() != $('#hftlp').val() ||
			$('#ftsp').val() != $('#hftsp').val() ||
			$('#ftde').val() != $('#hftde').val() ||
			$('#ftr').val() != $('#hftr').val()
		){
			return true;
		}else{
			return false;
		} 
	}  
	
	
	//if the value was changed for albums
	function checkIfChangedAlbum(){ 
		if(
  			$('#faat').val() != $('#hfaat').val() ||
  			$('#fare').val() != $('#hfare').val() || 
  			$('#faa').val() != $('#hfaa').val() || 
			$('#fal').val() != $('#hfal').val() ||
			$('#fat').val() != $('#hfat').val() ||
			$('#fade').val() != $('#hfade').val() ||
			$('#facp').val() != $('#hfacp').val() ||
			$('#falp').val() != $('#hfalp').val() ||
			$('#fasp').val() != $('#hfasp').val() || 
			$('#fagre').val() != $('#hfagre').val() || 
			$('#faimg').val() != $('#hfaimg').val() || 
			$('#far').val() != $('#hfar').val()
		){
			return true;
		}else{
			return false;
		}
	}
	
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