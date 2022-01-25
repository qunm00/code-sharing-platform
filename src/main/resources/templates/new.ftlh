<html>
    <head>
        <title>Create</title>
        <style>
            .error{
                width: 100%;
                padding: 0;

                font-size: 80%;
                color: white;
                background-color: #900;
                border-radius: 0 0 5px 5px;

                box-sizing: border-box;
            }

            .error.active {
                padding: 0.3em;
            }
        </style>

    </head>
    <body>
        <form novalidate>
            <p>
                <label for="code_snippet">
                    <span>Code Snippet</span>
                    <textarea id="code_snippet" required></textarea>
                    <span class="error" aria-live="polite"></span>
                </label>
            </p>

            <p>
                <label for="time_restriction">
                    <span>Time restriction</span>
                    <input id="time_restriction" type="text" pattern="[0-9]*" />
                    <span class="error" aria-live="polite"></span>
                </label>
            </p>

            <p>
                <label for="views_restriction">
                    <span>Views restriction</span>
                    <input id="views_restriction" type="text" pattern="[0-9]*" />
                    <span class="error" aria-live="polite"></span>
                </label>
            </p>

            <input type="submit" value="Submit" />
        </form>

        <script>
            const code_snippet = document.getElementById('code_snippet');
            const code_snippet_error = document.querySelector('#code_snippet + span.error');

            code_snippet.addEventListener('input', function (event) {
                if (code_snippet.validity.valid) {
                    code_snippet_error.textContent = '';
                    code_snippet_error.className = 'error';
                } else {
                    showCodeSnippetError();
                }
            });

            function showCodeSnippetError() {
                if (code_snippet.validity.valueMissing) {
                    code_snippet_error.textContent = "Code snippet can't be empty";
                }
                code_snippet_error.classname = 'error active';
            }

            const time_restriction = document.getElementById('time_restriction');
            const time_restriction_error = document.querySelector('#time_restriction + span.error');

            time_restriction.addEventListener('input', function (event) {
                if (time_restriction.validity.valid) {
                    time_restriction_error.textContent = '';
                    time_restriction_error.className = 'error';
                } else {
                    showTimeRestrictionError();
                }
            });

            function showTimeRestrictionError() {
                if (time_restriction.validity.patternMismatch) {
                    time_restriction_error.textContent = 'Entered value needs to be a number';
                }
                time_restriction_error.classname = 'error active';
            }

            const views_restriction = document.getElementById('views_restriction');
            const views_restriction_error = document.querySelector('#views_restriction + span.error');

            views_restriction.addEventListener('input', function (event) {
                if (views_restriction.validity.valid) {
                    views_restriction_error.textContent = '';
                    views_restriction_error.className = 'error';
                } else {
                    showViewsRestrictionError();
                }
            });

            function showViewsRestrictionError() {
                if (views_restriction.validity.patternMismatch) {
                    views_restriction_error.textContent = 'Entered value needs to be a number';
                }
                views_restriction_error.classname = 'error active';
            }

            const form = document.getElementsByTagName('form')[0];
            form.addEventListener('submit', function (event) {
                if (!time_restriction.validity.valid
                || !views_restriction.validity.valid
                || !code_snippet.validity.valid) {
                    showCodeSnippetError();
                    showTimeRestrictionError();
                    showViewsRestrictionError();
                    event.preventDefault();
                } else {
                    send();
                }
            })

            function send() {
                const code = document.getElementById("code_snippet").value;
                const time = document.getElementById("time_restriction").value;
                const views = document.getElementById("views_restriction").value;

                const object = {
                    "code": code,
                    "time": time === '' ? 0 : time,
                    "views": views === '' ? 0 : views
                };

                const json = JSON.stringify(object);

                const xhr = new XMLHttpRequest();
                xhr.open("POST", '/api/code/new', false)
                xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                xhr.send(json);

                const snippet_id = JSON.parse(xhr.responseText).id

                if (xhr.status == 200) {
                  alert("Success! This snippet's id is " + snippet_id);
                }
            }
        </script>
    </body>
</html>