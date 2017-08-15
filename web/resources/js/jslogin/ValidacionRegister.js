//$(document).ready(function () {
//    $('#datePicker')
//        .datepicker({
//            autoclose: true,
//            format: 'dd/mm/yyyy'
//        })
//        .on('changeDate', function (e) {
//            // Revalidate the date field
//            $('#RegisterUsuario').formValidation('revalidateField', 'date');
//        });
//
//    $('#RegisterUsuario').formValidation({
//        framework: 'bootstrap',
//        icon: {
//            valid: 'glyphicon glyphicon-ok',
//            invalid: 'glyphicon glyphicon-remove',
//            validating: 'glyphicon glyphicon-refresh'
//        },
//        fields: {
//            cedula: {
//                validators: {
//                    notEmpty: {
//                        message: 'Numero de Documento es Requerido'
//                    },
//                    stringLength: {
//                        min: 10,
//                        max: 15,
//                        message: 'El Numero de Documento debe tener más de 6 y menos de 15 caracteres'
//                    },
//                    regexp: {
//                        regexp: /^[0-9_\.]+$/,
//                        message: 'El Numero de Documento solo acepta numeros'
//                    }
//                }
//            },
//            Username: {
//                validators: {
//                    notEmpty: {
//                        message: 'El Nombre de Usuario es obligatorio'
//                    },
//                    stringLength: {
//                        min: 6,
//                        max: 15,
//                        message: 'El nombre de usuario debe tener más de 6 y menos de 15 caracteres'
//                    },
//                    regexp: {
//                        regexp: /^[a-zA-Z0-9_\.]+$/,
//                        message: 'El nombre de usuario sólo puede consistir de alfabético, número, punto y subrayado'
//                    }
//                }
//            },
//
//            nombre: {
//                // row: '.col-xs-4',
//                validators: {
//                    notEmpty: {
//                        message: 'El campo Nombre es obligatorio'
//                    },
//                     stringLength: {
//                        min: 6,
//                        max: 30,
//                        message: 'El nombre de usuario debe tener más de 6 y menos de 30 caracteres'
//                    },
//                    regexp: {
//                        regexp: /^[a-z\s]+$/i,
//                        message: 'El nombre completo puede consistir en caracteres alfabéticos y espacios solamente'
//                    }
//                }
//            },
//
//
//
//            email: {
//                validators: {
//                    notEmpty: {
//                        message: 'El correo electronico es obligatorio'
//                    },
//
//                        regexp: {
//                            regexp: '^[^@\\s]+@([^@\\s]+\\.)+[^@\\s]+$',
//                            message: 'La dirección de correo electrónico no válida.'
//                    }
//                }
//            },
//
//             date: {
//                validators: {
//                     notEmpty: {
//                         message: 'La fecha de nacimiento es obligatoria'
//                     },
//                     date: {
//                         format: 'DD/MM/YYYY',
//                         message: 'La fecha de nacimiento no es válida'
//                     }
//                 }
//            },
//            contraseña: {
//                validators: {
//                    notEmpty: {
//                        message: 'La contraseña es obligatoria'
//                    },
//                    callback: {
//                        callback: function (value, validator, $field) {
//                            var password = $field.val();
//                            if (password == '') {
//                                return true;
//                            }
//
//                            var result = zxcvbn(password),
//                                score = result.score,
//                                message = result.feedback.warning || 'La contraseña es debil';
//
//                            // Update the progress bar width and add alert class
//                            var $bar = $('#strengthBar');
//                            switch (score) {
//                                case 0:
//                                    $bar.attr('class', 'progress-bar progress-bar-danger')
//                                        .css('width', '1%');
//                                    break;
//                                case 1:
//                                    $bar.attr('class', 'progress-bar progress-bar-danger')
//                                        .css('width', '25%');
//                                    break;
//                                case 2:
//                                    $bar.attr('class', 'progress-bar progress-bar-danger')
//                                        .css('width', '50%');
//                                    break;
//                                case 3:
//                                    $bar.attr('class', 'progress-bar progress-bar-warning')
//                                        .css('width', '75%');
//                                    break;
//                                case 4:
//                                    $bar.attr('class', 'progress-bar progress-bar-success')
//                                        .css('width', '100%');
//                                    break;
//                            }
//
//                            // We will treat the password as an invalid one if the score is less than 3
//                            if (score < 3) {
//                                return {
//                                    valid: false,
//                                    message: message
//                                }
//                            }
//
//                            return true;
//                        }
//                    }
//                }
//            },
//
//        }
//    });
//});
