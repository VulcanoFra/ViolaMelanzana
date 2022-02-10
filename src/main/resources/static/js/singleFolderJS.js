function modalFolderFunction (modal){	
        $(modal).modal();
		
}

$(document).ready(function(){			
	var count = 0;
	$.ajax({
		type: "GET",
		url: "/folder",

		success: function(risposta) { 		
					for (var j = 0; j < 4; j++) {

						if (j < risposta.length) {
								$('#likeRecipe').append($('<div class="card mb-3 vm-card" id="orizCard_'+ risposta[j].id +'">'+
										'<div class="row no-gutters">'+
										'<div class="col-md-4">'+
										'<a href="recipePage?ricetta_id=' + risposta[j].id + '">'+
										'<img src="' + risposta[j].base64Image + '"class="card-img" alt="..." id="cardImg">' +
										'</a>' +
										'</div>'+
										'<div class="col-md-8">'+
										'<div class="card-body">'+ 
										'<div class="icon-top-right">'+
										'<button id="removeRecipeFromFolder" name="removeRecipeFromFolder" value="'+risposta[j].id+ '" onClick="removeRecipe(' + risposta[j].id + ')">'+'Rimuovi</button>' +
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
		url: "/folder",

		success: function(risposta) { 
				
					if (count < risposta.length){
						for (var j = count1; j < count1+4; j++) {
							if (j < risposta.length) {
									$('#likeRecipe').append($('<div class="card mb-3 vm-card" id="orizCard_'+ risposta[j].id +'">'+
										'<div class="row no-gutters">'+
										'<div class="col-md-4">'+
										'<a href="recipePage?ricetta_id=' + risposta[j].id + '">'+
										'<img src="' + risposta[j].base64Image + '"class="card-img" alt="..." id="cardImg">' +
										'</a>' +
										'</div>'+
										'<div class="col-md-8">'+
										'<div class="card-body">'+ 
										'<div class="icon-top-right">'+
										'<button type="submit" id="removeRecipeFromFolder" name="removeRecipeFromFolder" value="'+risposta[j].id+ '" onClick="removeRecipe(' + risposta[j].id + ')">'+'Rimuovi</button>' +
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

function removeRecipe(idRicetta){ 
	
	var res= confirm('Sicuro di voler rimuovere quetsa ricetta dalla raccolta?');
	if (res){
		$.ajax({
			type: "POST",
			url: "/removeFromFolder",
			data: {
				id_ricetta: idRicetta
				
			},
			success: function(risposta) { 
				if(risposta== "OK"){
					alert('Ricetta rimossa correttamente')
					$("#orizCard_"+ idRicetta).remove();
				}
				else 
					alert('Ricetta rimossa correttamente');
			}
		});	
	}
}		


