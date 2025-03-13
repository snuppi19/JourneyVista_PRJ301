


function swapValues() {
const fromSelect = document.getElementById('fromSelect');
        const toSelect = document.getElementById('toSelect');
        const fromValue = fromSelect.value;
        const toValue = toSelect.value;
        fromSelect.value = toValue;
        toSelect.value = fromValue;
        }

function ReturnDate() {
var checkbox = document.getElementById('roundTripCheckbox');
        var returnDateInput = document.getElementById('returnDate');
        if (checkbox.checked) {
returnDateInput.disabled = false;
} else {
returnDateInput.disabled = true;
}

}






