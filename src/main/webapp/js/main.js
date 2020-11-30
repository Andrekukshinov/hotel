function languageSubmit() {
    let input = document.getElementsByClassName("lang");
    console.log('event');
    for (let runner = 0; runner < input.length; ++runner) {
        console.log(input[runner]);
        input[runner].addEventListener('change', function (e) {
            let form = document.getElementById("formLang");
            console.log('event');
            form.submit();
        }, false)
    }
//Change event listener
}
