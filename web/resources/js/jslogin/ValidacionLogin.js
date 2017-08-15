$(document).ready(function() {
    $('#loginUsuario').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            Username: {
                validators: {
                    notEmpty: {
                        message: 'El Nombre de Usuario es obligatorio'
                    },
                    stringLength: {
                        min: 6,
                        max: 15,
                        message: 'El nombre de usuario debe tener más de 6 y menos de 15 caracteres'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: 'El nombre de usuario sólo puede consistir de alfabético, número, punto y subrayado'
                    }
                }
            },
            Password: {
                validators: {
                    notEmpty: {
                        message: 'La contraseña es obligatoria y no puede estar vacía.'
                    },
                    password: {
                        message: 'La contraseña no es valida'
                    }
                }
            }
        }
    });
});