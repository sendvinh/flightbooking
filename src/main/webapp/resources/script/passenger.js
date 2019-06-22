function back() {
    $('#seatOption').submit();
}
;
function next() {
    if ($('#modelWrapper').valid()) {
        $('#modelWrapper').submit();
    }
}
;

$(function () {
    $('.date').datepicker({format: 'dd/mm/yyyy'});
});

//validate
$.validator.addMethod("firstNameRequired", function (value, element) {
    return value.indexOf(" ") != 0 && value != "";
}, "First name is required");
$.validator.addClassRules("firstName", {firstNameRequired: true});

$.validator.addMethod("lastNameRequired", function (value, element) {
    return value.indexOf(" ") != 0 && value != "";
}, "Last name is required");
$.validator.addClassRules("lastName", {lastNameRequired: true});
$.validator.addMethod("birthRequired", $.validator.methods.required, "Date of birth is required");
$.validator.addMethod("birthFormat",
        function (value) {
            return value.match(/^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/);
        }, "Format must be dd/mm/yyyy");
$.validator.addClassRules("birth", {birthRequired: true, birthFormat: true});

$.validator.addMethod("infantNameRequired",
        function (value, element) {
            if ($(element).parent().prev().find('input:checked').val() == "false") {
                return true;
            } else {
                return value.indexOf(" ") != 0 && value != "";
            }
        },
        "Infant name is required");
$.validator.addClassRules("infantName", {infantNameRequired: true});

$.validator.addMethod("emailRequired", $.validator.methods.required, "Email is required");
$.validator.addMethod("emailFormat",
        function (value) {
            return value.match(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/);
        }, "Wrong email format");
$.validator.addClassRules("email", {emailRequired: true, emailFormat: true});

$.validator.addMethod("phoneRequired", $.validator.methods.required, "Phone is required");
$.validator.addMethod("phoneFormat",
        function (value) {
            return value.match(/^\d{10}$/);
        }, "Phone must be 10 digits");
$.validator.addClassRules("phone", {phoneRequired: true, phoneFormat: true});

$(document).ready(function () {
    $('#modelWrapper')
            .validate({
                errorClass: "error",
                errorElement: "div",
                errorPlacement: function (error, element) {
                    if (element.hasClass('birth')) {
                        error.appendTo(element.parent().parent().next());
                    } else {
                        error.appendTo(element.parent().next());
                    }
                },
                highlight: function (element, errorClass) {
                    $(element).removeClass(errorClass);
                }
            });
});