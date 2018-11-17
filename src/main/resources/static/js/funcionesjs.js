/**
 * 
 */

$(document).ready(function(){
	
	$('.btn-borrar').each(function(){
		$(this).on('click', function(event){
			var respuesta = confirm("Se va a borrar el contacto. ¿Deseas continuar?");
			if (respuesta==false) {
				event.preventDefault();
				return false;
			} 
		});
	});

    $('input[type="tel"]').on('keyup', function(){
        var telefono = $(this).val();
        if(telefono.length>9){
            $(this).val(telefono.substring(0,9));
        }
    });

});