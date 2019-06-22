function back() {
    $('#passengerInfo').submit();
}
function next() {
    if (!timeout) {
        $('#paymentMethod').submit();
    } else {
        return;
    }
}
;