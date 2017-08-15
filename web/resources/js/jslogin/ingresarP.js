
$().ready(function(){
      $('#btnPersonal').click(function(e){
        e.preventDefault();
        usuario = $('#Username').val();
        clave = $('#Password').val();


        if(usuario == 'personal' && clave == '5678'){
          console.log(usuario+''+clave)
          window.location.href = 'prueba.html';
        }
  });
    });
