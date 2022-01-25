<html>
    <head>
        <link rel="stylesheet"
              href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
        <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>
        <title>Latest</title>
    </head>
    <body>
        <#list snippets as snippet>
            <span id="load_date">${snippet.date}</span>
            <pre id="code_snippet"><code>${snippet.code}</code></pre>
        </#list>
    </body>
</html>