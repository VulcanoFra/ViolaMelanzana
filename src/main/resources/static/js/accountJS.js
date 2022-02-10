$(document).ready(function(){
	
	// Get the element with id="defaultOpen" and click on it
	document.getElementById("defaultOpen").click();
	
	loadAddress();
	
	loadOrders();
	
		
	var saveData = document.querySelector("#btn-save-data");
	
	saveData.addEventListener("click", function(event){
		event.preventDefault();
		
		var nameInput = $("#name");
		var lastNameInput = $("#lastName");
		
		nameUser = nameInput.val();
		lastNameUser = lastNameInput.val();
		
		$.ajax({
		type: "POST",
		url: "/data",
		data : {
        		"name" : nameUser,
        		"lastName" : lastNameUser
    		},
		
		success: function(risposta) { 
				if( risposta.nome === nameUser ) {
					var userData = document.querySelector("#user-Data");
					userData.innerHTML = "";
					userData.append(risposta.nome + " " + risposta.cognome);
					 $('#dataModal').modal('hide');
						
				} else if ( risposta.status === "Auth" ) {
					alert("Modifiche non salvate.");
				}
				
			}
		});	
	})
		
		
		
	var saveUsername = document.querySelector("#btn-save-username");
	
	saveUsername.addEventListener("click", function(event){
		event.preventDefault();
		
		var ok = true;
		
		var usernameInput = $("#newUsername");
		var newUsername = usernameInput.val();
		
		var lblNewUsername = document.querySelector("#lblNewUsername");
		
		var regexUsername = /^[a-zA-Z0-9._-]+$/
		
		if(newUsername.length < 4){
			usernameInput.addClass("makeRed");
			lblNewUsername.innerHTML = "Username troppo corto";
			ok = false;
		} else if(newUsername.length > 20){
			usernameInput.addClass("makeRed");
			lblNewUsername.innerHTML = "Username troppo lungo";
			ok = false;
		} else if(!regexUsername.test(newUsername)){
			usernameInput.addClass("makeRed");
			lblNewUsername.innerHTML = "Lo Username può avere solo lettere, numeri o (_-.)";
			ok = false;
		} else {
			usernameInput.removeClass("makeRed");
			lblNewUsername.innerHTML = "";
		}
		
		$.ajax({
			async: false,
			type: "POST",
			url: "/checkUsername",
			data : {
	        		"newUsername" : newUsername
	    		},
			
			success: function(risposta) { 
				 if ( risposta.status === "exist" ) {
					ok = false;
					lblNewUsername.innerHTML = risposta.messaggio;
				}
				
			}
		});	
		
		if (ok) {
			$.ajax({
				type: "POST",
				url: "/username",
				data : {
		        		"newUsername" : newUsername
		    		},
				
				success: function(risposta) { 
						if( risposta.status === "Auth" ) {
							var userData = document.querySelector("#user-Username");
							userData.innerHTML = "";
							userData.append(newUsername);
							
							
							var userCurrent = document.querySelector("#current_username");
							userCurrent.innerHTML = "";
							userCurrent.append(newUsername);
							
							$('#usernameModal').modal('hide');
								
						} else if ( risposta.status === "nonAuth" ) {
							alert(risposta.messaggio);
						}
						
					}
			});	
		}
	

	})
	
	
	
	var save = document.querySelector("#btn-save");
	
	save.addEventListener("click", function(event){
		event.preventDefault();
		
		var ok = true;
		
		var oldPassword = $("#oldPassword");
		
		$.ajax({
		type: "POST",
		url: "/verifyPassword",
		data : {
        		"password" : oldPassword.val()
    		},
		
		success: function(risposta) { 

				if( risposta.status === "nonAuth" ) {
					oldPassword.addClass("makeRed");
					lblOldPassword.innerHTML = risposta.messaggio;
					ok = false;
					
					var newPassword = $("#newPassword");
					var lblPassword = document.querySelector("#lblNewPassword");
					
					ok = control(newPassword, lblPassword);
					
					
					var confirmPassword = $("#confirmpassword");
					var lblConfirmPassword = document.querySelector("#lblConfirmPassword");
					
					ok = control(confirmPassword, lblConfirmPassword);
					
				
					if(ok){
						if(newPassword.val() !== confirmPassword.val()){
							lblNewPassword.innerHTML = "Password diverse";
							lblConfirmPassword.innerHTML = "Password diverse";
							newPassword.addClass("makeRed");
							confirmPassword.addClass("makeRed");
							ok = false;
						} else{
							newPassword.removeClass("makeRed");
							confirmPassword.removeClass("makeRed");
						}
					}
					
				} else if ( risposta.status === "Auth" ) {
					
					oldPassword.removeClass("makeRed");
					lblOldPassword.innerHTML = "";
					
					var newPassword = $("#newPassword");
					var lblPassword = document.querySelector("#lblNewPassword");
					
					ok = control(newPassword, lblPassword);
					
					
					var confirmPassword = $("#confirmpassword");
					var lblConfirmPassword = document.querySelector("#lblConfirmPassword");
					
					ok = control(confirmPassword, lblConfirmPassword);
					
					if(ok){
						if(newPassword.val() !== confirmPassword.val()){
							lblNewPassword.innerHTML = "Password diverse";
							lblConfirmPassword.innerHTML = "Password diverse";
							newPassword.addClass("makeRed");
							confirmPassword.addClass("makeRed");
							ok = false;
						} else{
							newPassword.removeClass("makeRed");
							confirmPassword.removeClass("makeRed");
						}
					}
					
					
					if(ok){
						
						$.ajax({
							type: "POST",
							url: "/password",
							data : {
					        		"newPassword" : newPassword.val()
					    		},
							
							success: function(risposta) { 
								if( risposta.status === "nonAuth" ) {
									alert(risposta.messaggio);
										
								} else if( risposta.status === "Auth" ) {
									$('#passwordModal').modal('hide');
								}
									
							}
						});
					}
				}
				
			}
		});	
	})
	
	
	var saveAddress = document.querySelector("#btn-save-address");
	
	saveAddress.addEventListener("click", function(event){
		event.preventDefault();
		
		var viaInput = $("#viaAdd");
		var numInput = $("#numAdd");
		var capInput = $("#capAdd");
		var cityInput = $("#cityAdd");
		var provInput = $("#provAdd");
		var telInput = $("#telAdd");
		
		var via = viaInput.val();
		var num = numInput.val();
		var cap = capInput.val();
		var city = cityInput.val();
		var prov = provInput.val();
		var tel = telInput.val();
		
		
		$.ajax({
		type: "POST",
		url: "/saveAddres",
		data : {
				"id" : 0,
        		"via" : via, 
				"num": num,
				"cap" : cap,
				"city" : city, 
				"prov" : prov, 
				"tel" : tel
    		},
		
		success: function(risposta) { 
			
				if( risposta.status === "nonAuth" ) {
					alert(risposta.messaggio);
						
				} else if( risposta.status === "Auth" ) {
					$('#addressModal').modal('hide');
					document.getElementById("address-tab").innerHTML = "";
					loadAddress();	
				}
				
			}
		});	
	})
	
	var modAddress = document.querySelector("#btn-mod-address");
	
	modAddress.addEventListener("click", function(event){
		event.preventDefault();
		
		var idInput = $("#idAddress");
		var viaInput = $("#via");
		var numInput = $("#num");
		var capInput = $("#cap");
		var cityInput = $("#city");
		var provInput = $("#prov");
		var telInput = $("#tel");
		
		var id = idInput.val();
		var via = viaInput.val();
		var num = numInput.val();
		var cap = capInput.val();
		var city = cityInput.val();
		var prov = provInput.val();
		var tel = telInput.val();
		
		$.ajax({
		type: "POST",
		url: "/saveAddres",
		data : {
				"id" : id,
        		"via" : via, 
				"num": num,
				"cap" : cap,
				"city" : city, 
				"prov" : prov, 
				"tel" : tel
    		},
		
		success: function(risposta) { 
			
				if( risposta.status === "nonAuth" ) {
					alert(risposta.messaggio);
						
				} else if( risposta.status === "Auth" ) {
					$('#addressModModal').modal('hide');
					document.getElementById("address-tab").innerHTML = "";
					loadAddress();	
				}
				
			}
		});	
	})
	
});










function loadAddress() {
	$.ajax({
	type: "GET",
	url: "/account/address",
	
	success: function(risposta) { 
			
			for (var i = 0; i < risposta.length; i++) {
				$('#address-tab').append($('<div class="address-container">'+
												'<div>'+
													'<div class="row">'+
										        		'<div class="col-md-6 col-sm-6 col-xs-6"> <h4>Via:</h4></div>'+
										            	'<div class="col-md-6 col-sm-6 col-xs-6" id="via-'+ risposta[i].id +'">' + risposta[i].indirizzo + '</div>'+
										            '</div>	'+													
													' <div class="row">'+
										        		'<div class="col-md-6 col-sm-6 col-xs-6"> <h4>Numero civico:</h64> </div>'+
										            	'<div class="col-md-6 col-sm-6 col-xs-6" id="num-'+ risposta[i].id +'">' + risposta[i].n_civico + '</div>'+
										            '</div>'+
										          	'<div class="row">'+
										        		'<div class="col-md-6 col-sm-6 col-xs-6"> <h64>CAP:</h4> </div>'+
										            	'<div class="col-md-6 col-sm-6 col-xs-6" id="cap-'+ risposta[i].id +'">' + risposta[i].cap + '</div>'+
										            '</div>'+
													'<div class="row">'+
										        		'<div class="col-md-6 col-sm-6 col-xs-6"> <h4>Città:</h4> </div>'+
										            	'<div class="col-md-6 col-sm-6 col-xs-6" id="city-'+ risposta[i].id +'">' + risposta[i].citta + '</div>'+
										            '</div>'+
										            '<div class="row">'+
										        		'<div class="col-md-6 col-sm-6 col-xs-6"> <h4>Provincia:</h4> </div>'+
										            	'<div class="col-md-6 col-sm-6 col-xs-6" id="prov-'+ risposta[i].id +'">' + risposta[i].provincia + '</div>'+
										            '</div>'+
										            '<div class="row">'+
										        		'<div class="col-md-6 col-sm-6 col-xs-6"> <h4>Telefono:</h4> </div>'+
										            	'<div class="col-md-6 col-sm-6 col-xs-6" id="tel-'+ risposta[i].id +'"> ' + risposta[i].telefono + ' </div>'+
										            '</div>'+
													'<div class="row center">'+
														'<button class="vm-btn-cart vm-btn-mod glyphicon glyphicon-trash vm-color" onclick="deleteAddress('+ risposta[i].id +')"></button>'+
														'<button class="vm-btn-cart vm-btn-mod glyphicon glyphicon-edit vm-color" onclick="modAddress('+ risposta[i].id +')"></button>'+
													'</div>'+
												'</div>'+
											'</div>'));
			}
			
			$('#address-tab').append($('<div class="row center">'+
											'<button id="ok" class="vm-btn-cart vm-btn-mod glyphicon glyphicon-plus vm-color"  data-toggle="modal" data-target="#addressModal"></button>'+
										'</div>'));
		}
	});
}

function loadOrders() {
	$.ajax({
		type: "GET",
		url: "/account/orders",
		
		success: function(risposta) { 
				
				for (var i = 0; i < risposta.length; i++) {
					$('#orders-tab').append($('<div class="row order">'+
													'<h3>Numero dell’ordine: ' + risposta[i].id + '</h3>'+
													'<div class="row"> '+
														'<div class="col-md-4 col-sm-4 col-xs-12">'+
															'<b>Data dell’ordine</b> <br> ' + risposta[i].data + ' </div>'+
														'<div class="col-md-4 col-sm-4 col-xs-12">'+
															'<b>Totale</b> <br> ' + risposta[i].totale + ' €</div>'+
														'<div class="col-md-4 col-sm-4 col-xs-12">'+
															'<b>Stato del ordine</b> <br> ' + risposta[i].stato + '</div>'+
													'</div>'+
												'</div>'));
				}
			}
	});
}

function control(elem, lab) {
		let regexPassword = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])");
		
		if(elem.val().length < 8 ){
			elem.addClass("makeRed");
			lab.innerHTML = "Password troppo corta";
			return false;
		} else if(elem.val().length > 20){
			elem.addClass("makeRed");
			lab.innerHTML = "Password troppo lunga";
			return false;
		} else if(!regexPassword.test(elem.val())){
			elem.addClass("makeRed");
			lab.innerHTML = "La password deve contenere almeno una lettera minuscola, una maiuscola, un numero e $%@#!*^&";
			return false;
		} else{
			elem.removeClass("makeRed");
			lab.innerHTML = "";
			return true;
		}
	}

function deleteAddress(id) {
	
	$.ajax({
		type: "POST",
		url: "/deleteAddress",
		data : {
	        		"addressId" : id
	    		},
		
		success: function(risposta) {
			
			if( risposta.status === "nonAuth" ) {
				alert(risposta.messaggio);			
			} else if ( risposta.status === "Auth" ) {
				document.getElementById("address-tab").innerHTML = "";
				loadAddress();	
			}
		}
	});	
}

function modAddress(id) {
	
	$.ajax({
		type: "GET",
		url: "/getAddress",
		data : {
	        		"addressId" : id
	    		},
		
		success: function(risposta) {
			
			var idAddress = document.getElementById("idAddress");
			idAddress.value = risposta.id;
			
			var via = document.getElementById("via");
			via.value = risposta.indirizzo;
			
			var num = document.getElementById("num");
			num.value = risposta.n_civico;
			
			var cap = document.getElementById("cap");
			cap.value = risposta.cap;
			
			var city = document.getElementById("city");
			city.value = risposta.citta;
			
			var prov = document.getElementById("prov");
			prov.value = risposta.provincia;
			
			var tel = document.getElementById("tel");
			tel.value = risposta.telefono;
			
			$('#addressModModal').modal('show');
		}
	});	
	
	
}










function load(evt, elem) {
	var tabcontent = document.getElementsByClassName("tabcontent");
	for (var i = 0; i < tabcontent.length; i++) {
	  tabcontent[i].style.display = "none";
	}
	
	var tablinks = document.getElementsByClassName("tablinks");
	for (var i = 0; i < tablinks.length; i++) {
	  tablinks[i].className = tablinks[i].className.replace(" active", "");
	}
	
	document.getElementById(elem).style.display = "block";
	evt.currentTarget.className += " active";
}

function profile(evt) {	
	load(evt, "Dati");
}
	
function address(evt) {
	
	load(evt, "address-tab");
}

function orders(evt) {
	load(evt, "orders-tab");
}
	
