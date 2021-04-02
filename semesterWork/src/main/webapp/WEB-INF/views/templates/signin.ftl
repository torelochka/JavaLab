<#ftl encoding="UTF-8"/>
<#import "layouts/base.ftl" as base>
<#import "spring.ftl" as spring/>
<@base.main css=["register.css"] title="Авторизация">
    <div class="content">
        <div class="container">
            <div class="register-cont_title">
                <@spring.message 'sign_in_page.title'/>
            </div>

            <#if error??>
                <div style="padding-top: 20px">
                    <div class="alert alert-danger fade show" role="alert">
                        <@spring.message 'sign_in_page.error'/>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>
            </#if>

            <form class="form-signin" method="post" action="/signIn" autocomplete="off">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="row cards">
                    <div class="card offset-3 col-md-6" style="border-radius: 20px">
                        <div class="card-body text-center">
                            <div class="register-cont_input_top">
                                <label for="inputEmail"><@spring.message 'sign_in_page.email'/></label>
                                <input type="email" id="inputEmail" class="form-control register-cont_input"
                                       required name="email">
                            </div>
                            <div class="register-cont_input_middle">
                                <label for="inputPassword"><@spring.message 'sign_in_page.password'/></label>
                                <input type="password" id="inputPassword" class="form-control register-cont_input"
                                       required name="password">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row cards">
                    <button class="offset-4 col-md-4 btn" type="submit">
                        <@spring.message 'sign_in_page.sign_in_button'/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</@base.main>