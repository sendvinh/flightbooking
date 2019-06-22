//validate
$(function () {
    $('input[name=tripType]').change(function () {
        if ($(this).val() == "oneWay") {
            $("#returnDateInput").attr('disabled', true);
        } else {
            $("#returnDateInput").attr('disabled', false);
        }
    });
});
//validate
$(function () {
    $('#departDate').datepicker({
        format: 'dd/mm/yyyy',
        startDate: '0d'
    }).on('changeDate', function (selected) {
        var departDate = new Date(selected.date.valueOf());
        $('#returnDate').datepicker('setStartDate', departDate);
        $('#returnDate').datepicker('setDate', departDate);
    });

    $('#returnDate').datepicker({
        format: 'dd/mm/yyyy'
    });
});

//submit
function next() {
    if ($('#bookingForm').valid())
        $('#bookingForm').submit();
}

//validate
$.validator.addMethod("dateFormat",
        function (value) {
            return value.match(/^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/);
        });
$.validator.addMethod("sameStation",
        function () {
            var departure = $('#departure').val();
            var arrival = $('#arrival').val();
            return departure != arrival;
        });

$(document).ready(function () {
    $('#bookingForm')
            .validate({
                errorClass: "error",
                errorElement: "div",
                rules: {
                    departDate: {
                        required: true,
                        dateFormat: true
                    },
                    returnDate: {
                        required: {
                            depends: function () {
                                var tripType = $("input[name='tripType']:checked").val();
                                if (tripType == 'roundTrip') {
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        },
                        dateFormat: {
                            depends: function () {
                                var tripType = $("input[name='tripType']:checked").val();
                                if (tripType == 'roundTrip') {
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }
                    },
                    departure: {
                        sameStation: true
                    },
                    arrival: {
                        sameStation: true
                    }

                },
                messages: {
                    departDate: {
                        required: "Depart date is required",
                        dateFormat: "Format must be dd/mm/yyyy"
                    },
                    returnDate: {
                        required: "Return date is required",
                        dateFormat: "Format must be dd/mm/yyyy"
                    },
                    departure: {
                        sameStation: "2 station must be different"
                    },
                    arrival: {
                        sameStation: "2 station must be different"
                    }
                },
                highlight: function (element, errorClass) {
                    $(element).removeClass(errorClass);
                }
            });
});
