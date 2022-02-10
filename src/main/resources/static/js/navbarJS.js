
$(document).ready(function(){
	$.ajax({
		type: "GET",
		url: "/categorie",
		
		success: function(risposta) { 
			
			for (var i = 0; i < risposta.length; i++) {
				$('#nav-bar-cat').append($('<option value=' + risposta[i].id + '> ' + risposta[i].nome + ' </option>'));
			}
		}		
			
	});

});