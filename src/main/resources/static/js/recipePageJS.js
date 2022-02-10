window.addEventListener("load", function(){
	eventMakeComment();
	eventCreateFolder();
	eventBuyRecipe();
	eventBan();
	eventRemoveRecipe();
})

function gestisciLike(button){
	var ricetta = $("#idRicetta").val();
	var mailUtente = $("#mailUtente").val();
	$.ajax({
		type: "POST",
		url: "/handleLike",
		data:{
			idRicetta: ricetta,
			mail: mailUtente
		},
		success:function(risposta){
			if(risposta === "LIKEOFF"){
				var like = document.querySelector(".likeUpNum");
				like.textContent  = +like.textContent - 1;
			} else if (risposta === "LIKEON"){
				var like = document.querySelector(".likeUpNum");
				like.textContent = +like.textContent + 1;
			}
			
			myFunction(button);
		}
	})
}

function myFunction(x) {
	  if ( x.classList.contains( "fa-heart") ) {
	        x.classList.remove( "fa-heart" );5
	        x.classList.add( "fa-heart-o" );
	    }
	    else {
	        x.classList.remove( "fa-heart-o" );
	        x.classList.add( "fa-heart" );
	    }
}	

function eventMakeComment(){
	console.log("save comment");
	var ricetta = $("#idRicetta").val();
	var mailUtente = $("#mailUtente").val();
	var username = $("#usernameUtente").val();
	
	var contenuto = $("#contenuto");
	var buttonComment = $("#submitComment");
	
	buttonComment.click(function(){
		
		var res = true;
		
		if(contenuto.val().length === 0){
			contenuto.addClass("makeRed");
			res = false;
		} else{
			contenuto.removeClass("makeRed");
		}
		
		if(res){
			$.ajax({
				type: "POST",
				url: "/saveComment",
				data:{
					contenuto: contenuto.val(),
					idRicetta: ricetta,
					mail: mailUtente,
					username: username
				},
				success: function(risposta){
					if(risposta == "OK"){
						var divCommenti = document.querySelector("#divCommenti");
						var html = divCommenti.innerHTML;
						divCommenti.innerHTML = "<div class=\"card\">" +
	  											 	"<div class=\"card-header\">" +
														 "<h1>" + username +":</h1>" + 
														 "<img src=\"/images/logo.png\"></img>" +
												 	 "</div>" +
													 "<div class=\"card-body\">" +
													 	"<p class=\"card-text \">" + contenuto.val() + "</p>" +
													 "</div>" +
													 "<div class=\"card-footer text-muted\" id=\"footer\">" + mailUtente + "</div>" +
												 "</div>" +
												 html;
						contenuto.val("");
					}
					else
						alert("Commento non aggiunto");
						
					
				},
				fail:function(xhr){
					alert("Ci scusiamo per il disagio. Riprova più tardi")
				}
			})
		}
	})
}

function eventAddToFolder(){
	var ricetta = $("#idRicetta").val();
	var mailUtente = $("#mailUtente").val();
	var raccolta = $('input[name="raccoltaSel"]:checked').val();
	
			$.ajax({
				type: "POST",
				url: "/addRecipe",
				data:{
					raccoltaSel: raccolta,
					idRicetta: ricetta,
					mail: mailUtente
				},
				success: function(risposta){
					console.log("addTOfolder " + raccolta.val());
					if(risposta == "AGGIUNTA"){
						alert("Ricetta aggiunta correttamente alla raccolta");
						$("#modalSave").modal('toggle');
						$("#formRaccolte_" + raccolta.val()).remove();

					}
					else{
						alert("Ricetta non aggiunta. Riprovare più tardi");
						$("#modalSave").modal('toggle');
					}
			   }
				
			});

}


function eventCreateFolder(){
	var mailUtente = $("#mailUtente").val();
	
	var nomeRaccolta = $("#nomeRaccolta");
	var button = $("#saveFolder");
	button.click(function(){
		var res=true;
		if(nomeRaccolta.val().length === 0){
			nomeRaccolta.addClass("makeRed");
			res = false;	
		} else
		nomeRaccolta.removeClass("makeRed");
	
		if(res){
			$.ajax({
				type: "POST",
				url: "/newFolderRec",
				data:{
					nome: nomeRaccolta.val(),
					mail: mailUtente
				},
				success: function(risposta){
					if(risposta == "CREATA"){
						$("#createNewFolder").modal('toggle');
						alert("Raccolta creata correttamente");
						$('#modalSelBody').append($(
							'<div class="form-check" id="formRaccolte_' + nomeRaccolta.val() + '">'+
								'<input name="raccoltaSel" type="radio" id="raccoltaSel" value="'+nomeRaccolta.val() + '" checked="checked">'+
								'<label for="' + nomeRaccolta.val()  + '">' + nomeRaccolta.val()  + '</label>'));
							'</div>'
					}
					
					if(risposta== "NO"){
						alert("Raccolta non creata. Riprovare più tardi");
						$("#createNewFolder").modal('toggle');
					}
					
					if(risposta== "ESISTE"){
						nomeRaccolta.addClass("makeRed");
						alert("Raccolta NON creata:: raccolta già esistente.");
					}
			   }
				
			});
		}
	})
}


function eventBuyRecipe(){
	var mailUtente = $("#mailUtente").val();
	
	var ricetta = $("#idRicetta").val();
	var button = $("#buyButton");
	button.click(function(){	
		var res= confirm('Alcuni ingredienti potrebbero non essere presenti nel nostro store.Aggiungere i prodotti disponibli?');
		console.log("res= " + res);
		if(res)	{
			$.ajax({
				type: "POST",
				url: "/addCart",
				data:{
					idRicetta: ricetta,
					mail: mailUtente
				},
				success: function(risposta){
					if(risposta == "ORDINE OK"){
						alert("Ingredienti aggiunti al carrello");
					}
					
					else
						alert("Si è verificato un errore. Riprovare più tardi");
					
			   }
				
			});
		}
	})
}

function eventBan(){
	var mailUtente = $("#mailUtente").val();
	var ricetta = $("#idRicetta").val();
	
	var button = $("#ban");
	button.click(function(){
		 var res=confirm('Sicuro di voler segnalare questa ricetta?');
		 if(res){
			var motivazione=$('input[name="segnalazione"]:checked').val();
			$.ajax({
				type: "POST",
				url: "/ban",
				data:{
					idRicetta: ricetta,
					mail: mailUtente,
					motivazione:motivazione
				},
				success: function(risposta){
					if(risposta == "BAN"){
						alert("La tua segnalazione è stata registrata.");
						$('#modalBan').modal('toggle');
						$('#banButton').hide();
						$('#banBar').append($('<button class="buttonType1" id="disabledButton" class="button disabled" title="Hai già segnalato questa ricetta" >'+	
							'<span class="fa fa-ban" aria-hidden="true" id="imgBut"></span>  Segnala'+
							'</button>'));
						
					}
					
					else{
						alert("Si è verificato un errore. Riprovare più tardi");
						$('#modalBan').modal('toggle');
					}
					
			   }
				
			});
		}
	})
}


function eventRemoveRecipe(){
	var mailUtente = $("#mailUtente").val();
	var ricetta = $("#idRicetta").val();
	
	var button = $("#removeR");
	
	button.click(function(){	
			var motivazione=$('input[name="remove"]:checked').val();
			$.ajax({
				type: "POST",
				url: "/removeRecipe",
				data:{
					remove:motivazione.val() ,
					idRicetta: ricetta,
					mail: mailUtente
				},
				success: function(risposta){
					if(risposta == "OK"){
						alert("La tua richiesta è stata registrata.");
						$('#modalRemoveRecipe').modal('toggle');
					}
					
					else{
						alert("Si è verificato un errore. Riprovare più tardi");
						$('#modalRemoveRecipe').modal('toggle');
					}
					
			   }
				
			});
	})
}

function removeRecipeMaster(){
	var res= confirm('Sicuro di voler eliminare DEFINITIVAMENTE la tua ricetta dal sito?');
	var ricetta = $("#idRicetta").val();
	console.log("ricetta "+ ricetta);
	if (res) {
		$.ajax({
			type: "POST",
			url: "/removeRecipeMaster",
			data:{
				idRicetta: ricetta,
			},
			success: function(risposta){
				if(risposta == "OK"){
					alert("La tua ricetta è stata eliminata.");
					location.href='/';
				}
				
				else{
					alert("Si è verificato un errore. Riprovare più tardi");
				}
				
		   }
			
		});	
	}
}



function modalFunction (modal){	
	$(modal).modal();	
}


function notLoggedFunction (){
    
     document.getElementById("overlay").style.display = "block";
	 $('.notification').show();
	 $('.close').click(function(){
	        $(".notification").hide();
			 document.getElementById("overlay").style.display = "none";
	 }); 
}
	