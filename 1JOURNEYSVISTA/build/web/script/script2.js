
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

            function openPassengerModal() {

                const modal = document.getElementById('passengerModal');
                modal.style.display = 'block';
            }

            function closePassengerModal() {
                const modal = document.getElementById('passengerModal');
                modal.style.display = 'none';
            }

            function changeCount(type, delta) {
                let countElement;
                if (type === 'adult') {
                    countElement = document.getElementById('adultCount');
                } else if (type === 'child') {
                    countElement = document.getElementById('childCount');
                } else {
                    countElement = document.getElementById('infantCount');
                }

                let currentCount = parseInt(countElement.innerText);
                currentCount += delta;

                if (currentCount < 0) {
                    currentCount = 0;
                }

                countElement.innerText = currentCount;
            }

            function confirmPassengers() {
                const adultCount = document.getElementById('adultCount').innerText;
                const childCount = document.getElementById('childCount').innerText;
                const infantCount = document.getElementById('infantCount').innerText;

                const passengerSelect = document.getElementById('passengerSelect');
                passengerSelect.options[0].innerText = `${adultCount} Người lớn, ${childCount} Trẻ em, ${infantCount} Phòng`;

                closePassengerModal();
            }


 