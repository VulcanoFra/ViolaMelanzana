window.addEventListener("load", function(){
	
	window.addEventListener('resize', function(){
		
		var w = window.screen.width;
		var elem = document.getElementById("tabella_prodotti");
		
  		if( w <= 400) {
			elem.className = "table-responsive";	
		} else {
			elem.className = "cart-container prod-container";
		}
	});

});


const { PDFDocument, StandardFonts, rgb } = PDFLib

    async function createPdf() {
      // Create a new PDFDocument
      const pdfDoc = await PDFDocument.create()

      // Embed the Times Roman font
      const timesRomanFont = await pdfDoc.embedFont(StandardFonts.TimesRoman)

      // Add a blank page to the document
      const page = pdfDoc.addPage()

      // Get the width and height of the page
      const { width, height } = page.getSize()

      // Draw a string of text toward the top of the page

		
		var prodotti_string = "Prodotti: \n";
		for(let i = 0; i < document.getElementsByClassName('nomeProdotti').length; ++i){
			prodotti_string +=  "		Nome: " + document.getElementsByClassName('nomeProdotti')[i].textContent + '\n' +
								"		Quantità: " + document.getElementsByClassName('quantita')[i].textContent + '\n' +
								"		Unità di Misura: " + document.getElementsByClassName('unitaDiMisura')[i].textContent + '\n' +
								"		Prezzo: " + document.getElementsByClassName('Prezzo')[i].textContent + '\n';
		}
	
      const fontSize = 12
      page.drawText('Numero dell’ordine: ' + document.getElementById("id_ordine").textContent + '\n' +
					'Data dell’ordine: ' + 	document.getElementById("data_ordine").textContent + '\n' +
					'Totale: ' + 	document.getElementById("totale_ordine").textContent + '\n' +
					'Stato dell’ordine: ' + 	document.getElementById("stato_ordine").textContent + '\n' + 
					prodotti_string + '\n \n \n' + 'Grazie per averci scelto\nViolaMelanzana', {
        x: 50,
        y: height - 4 * fontSize,
        size: fontSize,
        font: timesRomanFont,
        color: rgb(0.30, 0, 0.60),
      })

      // Serialize the PDFDocument to bytes (a Uint8Array)
      const pdfBytes = await pdfDoc.save()

			// Trigger the browser to download the PDF document
      download(pdfBytes, "RiepilogoOrdine.pdf", "application/pdf");
    }