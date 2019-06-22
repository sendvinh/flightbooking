$.validator.addMethod("isRequired", function (value, element) {
    return value.indexOf(" ") != 0 && value != "";
});

$(document).ready(function () {
    $('#payment')
            .validate({
                errorClass: "error",
                errorElement: "div",
                rules: {
                    accountNo: {
                        isRequired: true
                    },
                    owner: {
                        isRequired: true
                    }
                },
                messages: {
                    accountNo: {
                        isRequired: "Account no is required"
                    },
                    owner: {
                        isRequired: "Owner is required"
                    }
                },
                errorPlacement: function (error, element) {
                    error.appendTo(element.parent().parent().children().last());
                },
                highlight: function (element, errorClass) {
                    $(element).removeClass(errorClass);
                }
            });
});