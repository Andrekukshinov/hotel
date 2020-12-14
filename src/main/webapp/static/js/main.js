function edit() {
    let buttons = document.getElementsByClassName("edit-description");
    for (let i = 0; i < buttons.length; i++) {
        buttons[i].addEventListener("click", () => {
                let description = document.getElementById("description" + (i + 1));
                description.style.visibility="visible";
            }
        )
    }
    let close = document.getElementsByClassName("modal-close");
    for (let i = 0; i < buttons.length; i++) {
        close[i].addEventListener("click", () => {
                let description = document.getElementById("description" + (i + 1));
                description.style.visibility="hidden";
            }
        )
    }
}
