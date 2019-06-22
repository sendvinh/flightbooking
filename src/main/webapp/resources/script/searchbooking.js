$.validator.addMethod("isRequired", function (value, element) {
    return value.indexOf(" ") != 0 && value != "";
});

$.validator.addMethod("emailFormat",
        function (value) {
            return value.match(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/);
        });

$.validator.addMethod("phoneFormat",
        function (value) {
            return value.match(/^\d{10}$/);
        });

$(document).ready(function () {
    $('#searchForm')
            .validate({
                errorClass: "error col-md-5",
                errorElement: "div",
                rules: {
                    bookingCode: {
                        isRequired: true
                    },
                    email: {
                        isRequired: true,
                        emailFormat: true
                    },
                    phone: {
                        isRequired: true,
                        phoneFormat: true
                    }
                },
                messages: {
                    bookingCode: {
                        isRequired: "Booking code is required"
                    },
                    email: {
                        isRequired: "Email is required",
                        emailFormat: "Wrong email format"
                    },
                    phone: {
                        isRequired: "Phone number is required",
                        phoneFormat: "Phone must have 10 numberic digit"
                    }
                },
                highlight: function (element, errorClass) {
                    $(element).removeClass(errorClass);
                }
            });
});