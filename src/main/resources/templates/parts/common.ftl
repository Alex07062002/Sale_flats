<#assign known = Session.SPRING_SECURITY_CONTEXT??>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    currentUserId = user.getId()>
<#else>
    <#assign
    name = "unknown"
    isAdmin = false
    currentUserId = -1>
</#if>
<#assign query= 2323>
<#if rnd??>
    <#assign query= rnd.nextInt(1000)>
</#if>
<#macro page>
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <title>Аренда недвижимости</title>
            <link href="/static/css/main.css?${query!''}" rel="stylesheet">
        </head>
        <body>
            <div class="wrapper">
                <div class="navbar elevation-4" style="height: 50px">
                    <div class="container navbar-list">
                        <a href="/contracts">Заказы</a>
                        <a href="/account">Кабинет</a>
                        <a href="/book">Бронирование</a>
                        <#if known>
                            <form method="post" action="/logout">
                                <input type="submit"  value="Выйти" style="color: red !important;">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                            </form>
                        </#if>
                    </div>
                </div>
                <#nested>
            </div>
        </body>
    </html>
</#macro>