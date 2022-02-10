function myFunction() {
	var elem = document.getElementById("cat_bar");
	
	if (elem.className === "vm-cat-content") {
		elem.className += " responsive";
	} else {
		elem.className = "vm-cat-content";
	}
}



$(document).ready(function(){
	
	var count = 0;
	var recent = 0;
	var chef = 0;
	
	$('#recent_previous').disabled = true;
	
	if ($('#recent_previous').className === "vm-nav-btn") {
		$('#recent_previous').className += " disabled";
	} else {
		$('#recent_previous').className = "vm-nav-btn"
	}	
	
	
	// ***** ***** ***** ***** ***** ***** ***** ***** *****
	// Chef
	$.ajax({
		type: "GET",
		url: "/categorie",
		
		success: function(risposta) { 
			
			for (var i = 0; i < risposta.length; i++) {
				$('#cat_bar').append($('<a href="/category?categoria=' + risposta[i].id + '"><b>' + risposta[i].nome + '</b></a>'));
			}
		}		
			
	});
	
	
	
	// ***** ***** ***** ***** ***** ***** ***** ***** *****
	// Chef
	$.ajax({
		type: "GET",
		url: "/tuttiChef",
		data: {
    		"offset" : chef*4
		},
		
		success: function(risposta) {
		
				chef++;
				
				$("#chef_previous").disabled = true;
				$("#chef_previous").className += " disabled";
				
				$("#chef-row").innerHTML = "";
					
				for (var i = 0; i < risposta.length; i++) {
					$('#chef-row').append($('<div class="col-md-3 col-sm-3 col-xs-12 center-space" id="c-' + risposta[i].id + '">'+
												'<div>'+
													'<h4><b>' + risposta[i].nome +'</b></h4>'+	
												'</div>'+									
												'<a href="/chef?key='+ risposta[i].id +'" >'+
													'<img src="'+ risposta[i].img_link +'" class="img-circle person" alt="Avatar">'+
												'</a>'+										
											'</div>'));
				}
		}
	});
	
	chef_previous.addEventListener("click", function() {
		
		document.getElementById("chef_next").disabled = false; 
		document.getElementById("chef_next").className = "vm-nav-btn";

		$.ajax({
			type: "GET",
			url: "/tuttiChef",
			data : {
        		"offset" : (chef-2) * 4
    		},
		
			success: function(risposta) {
					chef--;
					
					if ( chef === 1 ) {
						document.getElementById("chef_previous").disabled = true; 
						document.getElementById("chef_previous").className += " disabled";
					}
				
					
					document.getElementById("chef-row").innerHTML = "";
					
					for (var i = 0; i < risposta.length; i++) {
					$('#chef-row').append($('<div class="col-md-3 col-sm-3 col-xs-12 center-space" id="c-' + risposta[i].id + '">'+
												'<div>'+
													'<h4><b>' + risposta[i].nome +'</b></h4>'+	
												'</div>'+									
												'<a href="/chef?key='+ risposta[i].id +'" >'+
													'<img src="'+ risposta[i].img_link +'" class="img-circle person" alt="Avatar">'+
												'</a>'+										
											'</div>'));
					}
			}
		});
		
	});
	
	chef_next.addEventListener("click", function() {
		
		document.getElementById("chef_previous").disabled = false; 
		document.getElementById("chef_previous").className = "vm-nav-btn";
				
		$.ajax({
			type: "GET",
			url: "/tuttiChef",
			data : {
        		"offset" : chef * 4
    		},
		
			success: function(risposta) {
					chef++;
					if ( risposta.length < 4 ) {
						document.getElementById("chef_next").disabled = true;
						document.getElementById("chef_next").className += " disabled";
					}
					
					if ( risposta.length !== 0 ) {
						document.getElementById("chef-row").innerHTML = "";	
					
						for (var i = 0; i < risposta.length; i++) {
						$('#chef-row').append($('<div class="col-md-3 col-sm-3 col-xs-12 center-space" id="c-' + risposta[i].id + '">'+
												'<div>'+
													'<h4><b>' + risposta[i].nome +'</b></h4>'+	
												'</div>'+									
												'<a href="/chef?key='+ risposta[i].id +'" >'+
													'<img src="'+ risposta[i].img_link +'" class="img-circle person" alt="Avatar">'+
												'</a>'+										
											'</div>'));
						}
					}
			}
		});
		
	});
	
	
	// ***** ***** ***** ***** ***** ***** ***** ***** *****
	// Popular Recipes
	$.ajax({
		type: "GET",
		url: "/ricettePopolari",
		data: {
    		"offset" : count*8
		},
		
		success: function(risposta) {
				for (var i = (0 + count * 2); i < (2 + count*2); i++) {
					
					$('#popular').append($('<div id="row-' + i + '" class="row"></div>'));
					
					for (var j = (0 + i * 4); j < (4 + i*4); j++) {
						
						$('#row-' + i).append($('<div class="col-md-3 col-sm-3 col-xs-12">'+
													'<div id="p-' + risposta[j].id + '"class="vm-card">'+
														'<a href="recipePage?ricetta_id=' + risposta[j].id + '">'+
															'<img src="' + risposta[j].base64Image + '" alt="Avatar" class="card-img">'+
														 '</a>'+
														 '<div id="chef-img-' + risposta[j].id + '"></div>'+
														 '<a href="recipePage?ricetta_id=' + risposta[j].id + '">'+
														 	'<div class="vm-container"><h4><b>' + risposta[j].titolo + '</b></h4></div>'+
														 '</a>'+
														 '<div class="vm-container-s">'+
															'<i class="glyphicon glyphicon-comment vm-color icon"></i>'+
															'<span class="number vm-color">' + risposta[j].commenti.length + '</span>'+
															'<i class="glyphicon glyphicon-heart vm-color icon"></i>'+
															'<span class="number vm-color">' + risposta[j].likes + '</span>'+
														 '</div>'+
													'</div>'+
												'</div>'));	
													
						if ( risposta[j].chefPubblicatore !== 0 ) {
							
							var id = risposta[j].id;
							
							$.ajax({
									type: "GET",
									url: "/singleChef",
									data: {
							    		"key" : risposta[j].chefPubblicatore
									},
									
									success: function(chef) {
										$('#chef-img-' + id).append($('<div>'+
																		'<img src="' + chef.img_link + '" alt="Avatar" class="img-circle avatar">'+
																	 '</div>'));		
									}
							});
							
						}
					}
				}
				
				count++;
		}
	});
	
	var countload = 0;

	load.addEventListener("click", function() {
		
		$.ajax({
			async: false,
			type: "GET",
			url: "/ricettePopolari",
			data: {
	    		"offset" : count*8
			},
			
		success: function(risposta) {
					if ( risposta.length < 8 ) {
						document.getElementById("load").disabled = true;
					}
					
					for (var i = 0; i < 2; i++) {
												
						$('#popular').append($('<div id="row-' + i + count * 2  + '" class="row"></div>'));
						
						for (var j = (0 + i * 4); j < (4 + i*4) && j < risposta.length; j++) {
							$('#row-' + i + count * 2).append($('<div class="col-md-3 col-sm-3 col-xs-12">'+
																	'<div id="p-' + risposta[j].id + '"class="vm-card">'+
																		'<a href="recipePage?ricetta_id=' + risposta[j].id + '">'+
																			'<img src="' + risposta[j].base64Image + '" alt="Avatar" class="card-img">'+
																		'</a>'+
																		'<div id="chef-img-' + risposta[j].id + '"></div>'+
																		'<a href="recipePage?ricetta_id=' + risposta[j].id + '">'+
																		 	'<div class="vm-container"><h4><b>' + risposta[j].titolo + '</b></h4></div>'+
																		'</a>'+
																		'<div class="vm-container-s">'+
																			'<i class="glyphicon glyphicon-comment vm-color icon"></i>'+
																			'<span class="number vm-color">' + risposta[j].commenti.length + '</span>'+
																			'<i class="glyphicon glyphicon-heart vm-color icon"></i>'+
																			'<span class="number vm-color">' + risposta[j].likes + '</span>'+
																		'</div>'+
																	'</div>'+
																'</div>'));
									
							var ricetta = risposta[j].id;
							
							if ( risposta[j].chefPubblicatore !== 0 ) {					
								$.ajax({
										async: false,
										type: "GET",
										url: "/singleChef",
										data: {
								    		"key" : risposta[j].chefPubblicatore
										},
										
										success: function(chef) {
											$('#chef-img-' + ricetta).append($('<div>'+
																			'<img src="' + chef.img_link + '" alt="Avatar" class="img-circle avatar">'+
																		 '</div>'));		
										}
								});
								
							}
						}
					}
					count++;
			}
		});
		
	});
	
	
	
	// ***** ***** ***** ***** ***** ***** ***** ***** *****
	// Recently Added
	$.ajax({
		type: "GET",
		url: "/ultimeRicette",
		data : {
    		"offset" : 0
		},
	
		success: function(risposta) {
				recent++;
				
				document.getElementById("recent_previous").disabled = true;
				document.getElementById("recent_previous").className += " disabled";
				
				stampa(risposta);
				
		}
	});
		
	
	
	recent_previous.addEventListener("click", function() {
		
		document.getElementById("recent_next").disabled = false; 
		document.getElementById("recent_next").className = "vm-nav-btn";
				
		$.ajax({
			type: "GET",
			url: "/ultimeRicette",
			data : {
        		"offset" : (recent-2) * 4
    		},
		
			success: function(risposta) {
					recent--;
					
					if ( recent === 1 ) {
						document.getElementById("recent_previous").disabled = true;
						document.getElementById("recent_previous").className += " disabled";
					}
					
					stampa(risposta);
			}
		});
		
	});
	
	recent_next.addEventListener("click", function() {
		
		document.getElementById("recent_previous").disabled = false; 
		document.getElementById("recent_previous").className = "vm-nav-btn";
				
		$.ajax({
			type: "GET",
			url: "/ultimeRicette",
			data : {
        		"offset" : recent * 4
    		},
		
			success: function(risposta) {
					recent++;
					
					if ( risposta.length < 4 ) {
						document.getElementById("recent_next").disabled = true;
						document.getElementById("recent_next").className += " disabled";
					}
					
					if ( risposta.length !== 0 ) {
						stampa(risposta);
					}
			}
		});
		
	});
});

function stampa(risposta) {
	document.getElementById("recent-row").innerHTML = "";
				
	for (var i = 0; i < risposta.length; i++) {
		$('#recent-row').append($('<div class="col-md-3 col-sm-3 col-xs-12"> <div id="r-' + risposta[i].id + '"class="vm-card"> </div></div>'));

		$('#r-' + risposta[i].id).append($('<a href="recipePage?ricetta_id=' + risposta[i].id + '">'+
											'<img src="' + risposta[i].base64Image + '" alt="Avatar" class="card-img">'+
										'</a>'+
										'<a href="recipePage?ricetta_id=' + risposta[i].id + '">'+
											'<div class="vm-container"><h4><b>' + risposta[i].titolo + '</b></h4></div>'+
									 	'</a>'+
									 	'<div class="vm-container-s">'+
									 		'<a href="#"><i class="glyphicon glyphicon-comment vm-color icon"></i></a>'+
									 		'<span class="number vm-color">' + risposta[i].commenti.length + '</span>'+
									 		'<a href="#"><i class="glyphicon glyphicon-heart vm-color icon"></i></a>'+
									 		'<span class="number vm-color">' + risposta[i].likes + '</span>'+
									 	'</div>'));
	}
}



var user = 1;




// ***** ***** ***** ***** ***** ***** ***** ***** *****
// Recipes Published by User
function previous() {
	
	document.getElementById("user_next").disabled = false; 
	document.getElementById("user_next").className = "vm-nav-btn";
	
	$.ajax({
		type: "GET",
		url: "/ricettePubblicateUtente",
		data : {
    		"offset" : (user-2) * 4
		},
	
		success: function(risposta) {
				user--;
				
				if ( user === 1 ) {
					document.getElementById("user_previous").disabled = true;
					document.getElementById("user_previous").className += " disabled";
				}
				
				document.getElementById("user-row").innerHTML = "";
					
				for (var i = 0; i < risposta.length; i++) {
					$('#user-row').append($('<div class="col-md-3 col-sm-3 col-xs-12"> <div id="u-' + risposta[i].id + '"class="vm-card"> </div></div>'));

					$('#u-' + risposta[i].id).append($('<a href="recipePage?ricetta_id=' + risposta[i].id + '">'+
														'<img src="' + risposta[i].base64Image + '" alt="Avatar" class="card-img">'+
													'</a>'+
													'<a href="recipePage?ricetta_id=' + risposta[i].id + '">'+
														'<div class="vm-container"><h4><b>' + risposta[i].titolo + '</b></h4></div>'+
												 	'</a>'+
												 	'<div class="vm-container-s">'+
												 		'<a href="#"><i class="glyphicon glyphicon-comment vm-color icon"></i></a>'+
												 		'<span class="number vm-color">' + risposta[i].commenti.length + '</span>'+
												 		'<a href="#"><i class="glyphicon glyphicon-heart vm-color icon"></i></a>'+
												 		'<span class="number vm-color">' + risposta[i].likes + '</span>'+
												 	'</div>'));
				}
				
		}
	});
}


function next() {
		
		document.getElementById("user_previous").disabled = false; 
		document.getElementById("user_previous").className = "vm-nav-btn";
							
		$.ajax({
			type: "GET",
			url: "/ricettePubblicateUtente",
			data : {
        		"offset" : user * 4
    		},
		
			success: function(risposta) {				
					user++;
					if ( risposta.length < 4 ) {
						document.getElementById("user_next").disabled = true;
						document.getElementById("user_next").className += " disabled";
					}
					
					if ( risposta.length !== 0 ) {
						document.getElementById("user-row").innerHTML = "";
							
						for (var i = 0; i < risposta.length; i++) {
							$('#user-row').append($('<div class="col-md-3 col-sm-3 col-xs-12"> <div id="u-' + risposta[i].id + '"class="vm-card"> </div></div>'));
		
							$('#u-' + risposta[i].id).append($('<a href="recipePage?ricetta_id=' + risposta[i].id + '">'+
																'<img src="' + risposta[i].base64Image + '" alt="Avatar" class="card-img">'+
															'</a>'+
															'<a href="recipePage?ricetta_id=' + risposta[i].id + '">'+
																'<div class="vm-container"><h4><b>' + risposta[i].titolo + '</b></h4></div>'+
														 	'</a>'+
														 	'<div class="vm-container-s">'+
														 		'<a href="#"><i class="glyphicon glyphicon-comment vm-color icon"></i></a>'+
														 		'<span class="number vm-color">' + risposta[i].commenti.length + '</span>'+
														 		'<a href="#"><i class="glyphicon glyphicon-heart vm-color icon"></i></a>'+
														 		'<span class="number vm-color">' + risposta[i].likes + '</span>'+
														 	'</div>'));
						}
					}
					
			}
		});
}
