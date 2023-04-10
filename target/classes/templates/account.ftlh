<#import "parts/common.ftl" as common>

<@common.page>
    <section>
        <div class="container">
            <div class="form">
                <form id="authForm" method="post" action="/edit/profile" class="auth">
                    <#if message ??>
                        <p style="color: red">${message}</p>
                    </#if>

                    <label>
                        <input name="nameSurname" placeholder="Имя" autocomplete="name" type="text" size="100" required
                               value="${(Session.SPRING_SECURITY_CONTEXT.authentication.principal.nameSurname)!''}">
                    </label>
                    <label>
                        <input name="login" placeholder="Login" autocomplete="login" type="text" size="50" required
                               value="${(Session.SPRING_SECURITY_CONTEXT.authentication.principal.login)!''}">
                    </label>
                    <label>
                        <input name="email" placeholder="Email" autocomplete="email" type="email" size="50" required
                               value="${(Session.SPRING_SECURITY_CONTEXT.authentication.principal.email)!''}">
                    </label>
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <label>
                        <input type="submit" value="Сохранить" class="sign-in-button elevation-4 ">
                    </label>
                </form>
            </div>
        </div>
    </section>
</@common.page>