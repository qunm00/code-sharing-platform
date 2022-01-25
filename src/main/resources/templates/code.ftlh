<html>
    <head>
        <link rel="stylesheet"
              href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
        <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>
        <title>Code</title>
    </head>
    <body>
        <span id="load_date">${snippet.date}</span><br>

        <#if snippet.time_expirable>
            <span id="time_restriction">${snippet.time_restriction}s</span>
        </#if>
        <#if snippet.views_expirable>
            <span id="views_restriction">${snippet.views_restriction}</span>
        </#if>

        <pre id="code_snippet"><code>${snippet.code}</code></pre>
    </body>
</html>