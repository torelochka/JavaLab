<#ftl encoding="UTF-8"/>
<#import "layouts/base.ftl" as base>
<#import "spring.ftl" as spring/>
<@base.main title="Регистрация" css=["register.css"]>
    <div class="content">
        <div class="container">
            <div class="register-cont_title">
                <@spring.message 'sign_up_page.registration.title'/>
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
            <@spring.bind "signUpForm"/>
            <form class="form-signin" method="post" action="/signUp" autocomplete="off">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="row cards">
                    <div class="card border-white offset-3 col-md-6 card_settings">
                        <div class="card-body text-center ">
                            <div class="register-cont_input_top">
                                <label for="lastname">
                                    <@spring.message 'sign_up_page.registration.lastname.placeholder'/>
                                </label>
                                <@spring.formInput "signUpForm.lastname" "class='form-control register-cont_input'"/>
                                <@spring.showErrors "<br>" "error"/>
                                <#--<input type="text" id="last_name" class="form-control register-cont_input"
                                       placeholder="<@spring.message 'sign_up_page.registration.lastname.placeholder'/>"
                                       required name="lastname" pattern="^[A-Za-zА-ЯЁа-яё]+$"
                                       title="<@spring.message 'sign_up_page.registration.lastname.title'/>">-->
                            </div>

                            <div class="register-cont_input_middle">
                                <label for="firstname">
                                    <label for="lastname"><@spring.message 'sign_up_page.registration.firstname.placeholder'/></label>
                                </label>
                                <@spring.formInput "signUpForm.firstname" "class='form-control register-cont_input'"/>
                                <@spring.showErrors "<br>" "error"/>
                                <#--<input type="text" id="first_name" class="form-control register-cont_input"
                                       placeholder="<@spring.message 'sign_up_page.registration.firstname.placeholder'/>"
                                       required name="firstname" pattern="^[A-Za-zА-ЯЁа-яё]+$"
                                       title="<@spring.message 'sign_up_page.registration.firstname.title'/>">-->
                            </div>

                            <div class="register-cont_input_middle">
                                <label for="email">
                                    <@spring.message 'sign_up_page.registration.email.placeholder'/>
                                </label>
                                <@spring.formInput "signUpForm.email" "class='form-control register-cont_input'"/>
                                <@spring.showErrors "<br>" "error"/>
                                <#--<input type="email" id="inputEmail" class="form-control register-cont_input"
                                       placeholder="<@spring.message 'sign_up_page.registration.email.placeholder'/>"
                                       title="<@spring.message 'sign_up_page.registration.email.title'/>"
                                       required name="email">-->
                            </div>

                            <div class="register-cont_input_middle">
                                <label for="password">
                                    <@spring.message 'sign_up_page.registration.password.placeholder'/>
                                </label>
                                <@spring.formPasswordInput "signUpForm.password" "class='form-control register-cont_input'"/>
                                <@spring.showErrors "<br>" "error"/>
                                <#if passwordsErrorMessage??>
                                    <p class="error">${passwordsErrorMessage}</p>
                                </#if>
                                <#--<input type="password" id="inputPassword" class="form-control register-cont_input"
                                       placeholder="<@spring.message 'sign_up_page.registration.password.placeholder'/>"
                                       required name="password"
                                       pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,}$"
                                       title="<@spring.message 'sign_up_page.registration.password.title'/>">-->
                            </div>

                            <div class="register-cont_input_bottom">
                                <label for="passwordAgain">
                                    <@spring.message 'sign_up_page.registration.password_again.placeholder'/>
                                </label>
                                <@spring.formPasswordInput "signUpForm.passwordAgain" "class='form-control register-cont_input'"/>
                                <#if passwordsErrorMessage??>
                                    <p class="error">${passwordsErrorMessage}</p>
                                </#if>
                                <#--<input type="password" id="repeatPassword" class="form-control register-cont_input"
                                       placeholder="<@spring.message 'sign_up_page.registration.password_again.placeholder'/>"
                                       required name="passwordAgain"
                                       pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,}$"
                                       title="<@spring.message 'sign_up_page.registration.password.title'/>">-->
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row register-cont_input_top">
                    <button class=" offset-4 col-md-4 btn" type="submit">
                        <@spring.message 'sign_up_page.registration.title'/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</@base.main>