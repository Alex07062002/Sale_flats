<#import "parts/common.ftl" as common>

<@common.page>
    <section>
        <div class="container">
            <div class="form">
                <form id="authForm" method="post" action="/login" class="auth">

                    <label>
                        <input name="username" autocomplete="email" type="tel" size="30" required>
                    </label>

                    <label>
                        <input name="password" autocomplete="current-password" type="password" size="30"
                               required>
                    </label>
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <label>
                        <input type="submit" value="Войти" class="sign-in-button elevation-4 ">
                    </label>
                    <p>
                        <a href="/registration">Зарегистрироваться</a>
                    </p>
                </form>
            </div>
        </div>
    </section>
</@common.page>