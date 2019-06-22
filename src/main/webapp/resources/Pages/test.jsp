<script>
$.validator.addMethod("dateFormat",
    function(value) {
        return value.match(/^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/);
    });


$(document).ready( function () {
    $('#modelWrapper')
    .validate({
        errorClass: "validate",
        errorElement: "div",
        errorPlacement: function(error, element) {
              error.appendTo(element.parent().next());
          },
        highlight: function(element, errorClass) {
            $(element).removeClass(errorClass);
        }
    });
});
</script>