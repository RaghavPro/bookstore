$(document).ready(function () {

    $('#registration-form').validate({
        rules: {
            inputUsername: {
                minlength: 5,
                required: true,
                maxlength: 20
            },
            inputFirstName: {
                required: true
            },
            inputPassword: {
                required: true,
                minlength: 6,
                maxlength: 20
            },
            inputconfirmPassword: {
                required: true,
                minlength: 6,
                equalTo: "#inputPassword"
            },
            inputEmail: {
                required: true,
                email: true
            },
            address: {
                minlength: 10,
                maxlength: 200,
                required: true
            },
        },
        highlight: function (element) {
            $(element).closest('.form-group').removeClass('has-success has-feedback').addClass('has-error');
        },
        success: function (element) {
            $(element).closest('.form-group').removeClass('has-error has-feedback').addClass('has-success has-feedback');
            $(element).remove();
            var identify = element.attr("for");
            $("span[for='" + identify + "']").removeClass("glyphicon glyphicon-remove form-control-feedback")
                .addClass("glyphicon glyphicon-ok form-control-feedback");
        }
    });

    $('#form-login').validate({
        rules: {
            username: {
                minlength: 5,
                required: true,
                maxlength: 20
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 20
            },
        },
        highlight: function (element) {
            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
        },
        success: function (element) {
            $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            $(element).remove();
        }
    });

    $('#search-form').validate({
        rules: {
            search: {
                required: true
            }
        },
        messages: {
            search: ""
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error').removeClass('has-success');
            $(element.form).find("label[for=" + element.id + "]")
                .addClass('has-error');
        },
        errorPlacement: function(error,element) { //Don't want to show error messages
            return true;
        }
    });


    $('#cart-form').validate({
        rules: {
            quantity: {
                maxlength: 1,
                digits: true,
                required: true
            }
        },
        highlight: function (element) {
            $(element).closest('.form-inline').addClass('has-error').removeClass('has-success');
            $(element.form).find("label[for=" + element.id + "]")
                .addClass('has-error');
        },
        errorPlacement: function(error,element) {
            return true;
        }
    });

    $('#nav-login').validate({
        rules: {
            username: {
                minlength: 5,
                required: true,
                maxlength: 20
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 20
            },
        },
        messages: {
            username: "",
            password: ""
        },
        highlight: function (element) {
            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
        },
        success: function (element) {
            $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            $(element).remove();
        },
        errorPlacement: function(error,element) {
            return true;
        }
    });


});