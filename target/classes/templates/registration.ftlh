<#import "parts/common.ftl" as common>

<#if usr??>
    <#assign surname = usr.surname()/>
    <#assign name = usr.name()/>
    <#assign email = usr.email()/>
    <#assign login = usr.login()/>
</#if>

<@common.page>
    <section>
        <div class="container">
            <div class="form">
                <form id="authForm" method="post" action="/registration" class="auth">
                    <#if message ??>
                        <p style="color: red">${message}</p>
                    </#if>


                    <label>
                        <input name="surname" placeholder="Фамилия" autocomplete="family-name" type="text" size="100"
                               required value="${surname!''}">
                    </label>

                    <label>
                        <input name="name" placeholder="Имя" autocomplete="name" type="text" size="100" required
                               value="${name!''}">
                    </label>
                    <label>
                        <input name="login" placeholder="Login" autocomplete="login" type="text" size="50" required
                               value="${login!''}">
                    </label>
                    <label>
                        <input name="email" placeholder="Email" autocomplete="email" type="email" size="50" required
                               value="${email!''}">
                    </label>
                    <label>
                        <input name="password" placeholder="Пароль" autocomplete="new-password" type="password"
                               size="50"
                               required>
                    </label>

                    <label>
                        <input name="passwordConfirm" placeholder="Подтвердите пароль" autocomplete="new-password"
                               type="password" size="50" required>
                    </label>
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <label>
                        <input type="submit" value="Зарегестрироваться" class="sign-in-button elevation-4 ">
                    </label>
                    <p>
                        <a href="/login"> Есть аккаунт? Войти</a>
                    </p>
                </form>
            </div>
        </div>
    </section>
</@common.page>