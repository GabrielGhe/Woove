/** 
 * @author 0737019
 */

var albumT;
  var trackT;
  
	$(function() {
		
		$('#showTracks').css('background-image', 'url(images/categoryPressed.png)');

		//genre carousel
		$('#genre-carousel1').scrollingCarousel({
			autoScroll : true
		});
		
		
		//dataTable plugin for track table 
		trackT = $('#tableLoop').dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers",
			"sDom": '<lf<t>ip>',
			'iDisplayLength': 8
		});
		
		
		/* Add a click handler to the rows - this could be used as a callback */
		$("#tableLoop tbody").click(function(event) {
			$(trackT.fnSettings().aoData).each(function (){
				$(this.nTr).removeClass('row_selected').css('background-color', 'white');
			});
			$(event.target.parentNode).addClass('row_selected').css('background-color', '#cc89b0');
			var anSelected = fnGetSelected( trackT );
			var idSelected = $(anSelected).children().eq(0).text();
			if(!isNaN(idSelected) && idSelected.trim() != ""){
				$('#hiddenFieldT').val(idSelected);
				$('#clickedARowT').submit();
			}
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
			if(!isNaN(idSelected) && idSelected.trim() != ""){
				$('#hiddenFieldA').val(idSelected);
				$('#clickedARowA').submit();
			}
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
		$('#showTracks').css('background-color', 'white');
		$('#showTracks').css('background-image', 'none');
		$('#showAlbums').css('background-image', 'url(images/categoryPressed.png)');
	}