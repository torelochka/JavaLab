<#ftl encoding="UTF-8"/>
<#import "layouts/base.ftl" as base>
<@base.main css=["register.css"] title="Авторизация">
    <div class="content">
        <div class="container">
            <div class="register-cont_title">
                Авторизация
            </div>

            <#if error??>
                <div style="padding-top: 20px">
                    <div class="alert alert-danger fade show" role="alert">
                        ${error}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>
            </#if>

            <form class="form-signin" method="post" action="/signIn" autocomplete="off">
                <div class="row cards">
                    <div class="card offset-3 col-md-6" style="border-radius: 20px">
                        <div class="card-body text-center">
                            <div class="register-cont_input_top">
                                <label for="inputEmail"></label>
                                <input type="email" id="inputEmail" class="form-control register-cont_input"
                                       placeholder="Почта" required name="email">
                            </div>
                            <div class="register-cont_input_middle">
                                <label for="inputPassword"></label>
                                <input type="password" id="inputPassword" class="form-control register-cont_input"
                                       placeholder="Пароль" required name="password">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row cards">
                    <button class="offset-4 col-md-4 btn" type="submit">Войти</button>
                </div>
            </form>
        </div>
    </div>
</@base.main>