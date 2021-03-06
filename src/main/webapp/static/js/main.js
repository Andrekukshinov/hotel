function validateForm(errors, invalidFieldCounter, inputs, consumer) {
    return (event) => {
        for (let i = 0; i < errors.length; i++) {
            errors[i].style.display = 'none';
        }
        for (let i = 0; i < inputs.length; i++) {
            let value = inputs[i].value;
            if (consumer(value)) {
                errors[i].style.display = 'block';
                invalidFieldCounter++;
            } else {
                errors[i].style.display = 'none';
            }
        }
        if (invalidFieldCounter !== 0) {
            invalidFieldCounter = 0;
            event.preventDefault();
        }
    };
}

function validateLogin() {
    let loginForm = document.getElementById("login-form");
    let inputs = document.getElementsByClassName("login-text");
    let errors = document.getElementsByClassName("space");
    let counter = 0;
    loginForm.addEventListener("submit", validateForm(errors, counter, inputs, (value) => value.includes(" ")));
}

function validateBooking() {
    let bookingForm = document.getElementById("booking-form");
    let arrival = document.getElementById("arrivalDate");
    let leaving = document.getElementById("leavingDate");
    let past = document.getElementById("past");
    let invalidDate = document.getElementById("invalidDate");
    let errors = [];
    let invalidFieldCounter = 0;
    errors.push(invalidDate, past)
    bookingForm.addEventListener("submit", (event) => {
        for (let i = 0; i < errors.length; i++) {
            errors[i].style.display = 'none';
        }
        let arrivalValue = arrival.value;
        let leavingValue = leaving.value;
        let arrivalDate = Date.parse(arrivalValue);
        let leavingDate = Date.parse(leavingValue);
        let currentDate = Date.now();
        if (arrivalDate < currentDate) {
            past.style.display = 'block';
            invalidFieldCounter++;
        } else if (leavingDate < arrivalDate) {
            invalidDate.style.display = 'block';
            invalidFieldCounter++;
        }
        if (invalidFieldCounter !== 0) {
            invalidFieldCounter = 0;
            event.preventDefault();
        }
    });
}

function validateNewRoom() {
    let roomForm = document.getElementById("room-form");
    let inputs = document.getElementsByClassName("room");
    let spaces = document.getElementsByClassName("space");
    let counter = 0;
    roomForm.addEventListener("submit", validateForm(spaces, counter, inputs, (value) => value <= 0));
}

function closeModal(i) {
    return () => {
        let description = document.getElementById("myModal" + (i + 1));
        description.style.display = "none";
    };
}

function confirmDisable() {
    let buttons = document.getElementsByClassName("open-modal");
    for (let i = 0; i < buttons.length; i++) {
        let button = buttons[i];
        if (button.type.toLowerCase() === "button" && button.tagName.toLowerCase() === "button") {
            button.addEventListener("click", () => {
                    let description = document.getElementById("myModal" + (i + 1));
                    description.style.display = "block";
                    window.onclick = function (event) {
                        if (event.target === description) {
                            description.style.display = "none";
                        }
                    }
                }
            )
        }
    }
    let close = document.getElementsByClassName("close");
    let reject = document.getElementsByClassName("reject");
    for (let i = 0; i < buttons.length; i++) {
        let closeElement = close[i];
        let rejectElement = reject[i];
        closeElement.addEventListener("click", closeModal(i));
        rejectElement.addEventListener("click", closeModal(i));
    }
}

function sleep(delay) {
    let start = new Date().getTime();
    while (new Date().getTime() < start + delay) ;
}
