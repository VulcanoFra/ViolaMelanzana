
function modalFolderFunction (modal){	
        $(modal).modal();
		
}


$(document).ready(function(){			
	var count = 0;
	$.ajax({
		type: "GET",
		url: "/like",

		success: function(risposta) { 		
					for (var j = 0; j < 4; j++) {
						if (j < risposta.length) {
								$('#likeRecipe').append($('<div class="card mb-3 vm-card" id="orizCard_"'+ risposta[j].id + '>'+
										'<div class="row no-gutters">'+
										'<div class="col-md-4 col-sl-6">'+
										'<a href="recipePage?ricetta_id=' + risposta[j].id + '">'+
										'<img src="' + risposta[j].base64Image + '"class="card-img" alt="..." id="cardImg">' +
										'</a>' +
										'</div>'+
										'<div class="col-md-8 col-xs-9">'+
										'<div class="card-body">'+ 
										'<div class="icon-top-right">'+
										'<i class="glyphicon glyphicon-comment vm-color icon"></i>'+
										'<span class="number vm-color">'+ risposta[j].commenti.length + '</span>' +
										'<i class="glyphicon glyphicon-heart vm-color icon"></i>' +
										'<span class="number vm-color">' + risposta[j].likes + '</span>' +
										'</div>' +
										'<a href="recipePage?ricetta_id=' + risposta[j].id + '">' +
										'<h2 id="title">' + risposta[j].titolo + '</h2>'+
										'</a>'+
										'<p class="decs">' + risposta[j].descrizione + '</p>' +
										'</div>'+
										'<div class="icon-botton-left">' +
										'<i class="glyphicon glyphicon-cutlery vm-color icon"></i>' +
										'<span class="number vm-color">' + risposta[j].difficolta + '</span>' +
										'<i class="glyphicon glyphicon-hourglass vm-color icon"></i>'+
										'<span class="number vm-color">' + risposta[j].tempoPreparazione + '</span>' +
										'</div>' +
										'</div>' +
										'</div>' +
										'</div>' ));
						}
						count++;
					}

		}									
		
	});
	
	load.addEventListener("click", function() {
		
		var count1=count;
		
		$.ajax({
		type: "GET",
		url: "/like",

		success: function(risposta) { 
					if (count < risposta.length){
						for (var j = count1; j < count1+4; j++) {
							if (j < risposta.length) {
									$('#likeRecipe').append($('<div class="card mb-3 vm-card" id="orizCard">'+
										'<div class="row no-gutters">'+
										'<div class="col-md-4">'+
										'<a href="recipePage?ricetta_id=' + risposta[j].id + '">'+
										'<img src="' + risposta[j].base64Image + '"class="card-img" alt="..." id="cardImg">' +
										'</a>' +
										'</div>'+
										'<div class="col-md-8">'+
										'<div class="card-body">'+ 
										'<div class="icon-top-right">'+
										'<form action="removeFromFolder" method="post" id="removeFromFolder">'+
										'</form>'+
										'<i class="glyphicon glyphicon-comment vm-color icon"></i>'+
										'<span class="number vm-color">'+ risposta[j].commenti.length + '</span>' +
										'<i class="glyphicon glyphicon-heart vm-color icon"></i>' +
										'<span class="number vm-color">' + risposta[j].likes + '</span>' +
										'</div>' +
										'<a href="recipePage?ricetta_id=' + risposta[j].id + '">' +
										'<h2 id="title">' + risposta[j].titolo + '</h2>'+
										'</a>'+
										'<p class="decs">' + risposta[j].descrizione + '</p>' +
										'</div>'+
										'<div class="icon-botton-left">' +
										'<i class="glyphicon glyphicon-cutlery vm-color icon"></i>' +
										'<span class="number vm-color">' + risposta[j].difficolta + '</span>' +
										'<i class="glyphicon glyphicon-hourglass vm-color icon"></i>'+
										'<span class="number vm-color">' + risposta[j].tempoPreparazione + '</span>' +
										'</div>' +
										'</div>' +
										'</div>' +
										'</div>' ));
							}
							count++;
						}
					}
		}									
		
		});
	});
});