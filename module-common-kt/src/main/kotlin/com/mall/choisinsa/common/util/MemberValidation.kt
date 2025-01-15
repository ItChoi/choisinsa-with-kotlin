package com.mall.choisinsa.common.util

class MemberValidation {
    companion object {
        /**
         * @param loginId -> 영문, 숫자 5 ~ 11자
         */
        fun isValidLoginId(loginId: String?): Boolean {
            val regex = Regex("^[a-zA-Z0-9]{5,11}$")
            return Validation.areAllNotBlank(loginId)
                    && regex.matches(loginId!!)
        }

        /**
         * @param password: 숫자, 영문, 특수문자 조합 최소 8자
         */
        fun isValidPassword(password: String?): Boolean {
            val regex = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
            return Validation.areAllNotBlank(password)
                    && regex.matches(password!!)
        }

        /**
         * @param email
         */
        fun isValidEmail(email: String?): Boolean {
            val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
            return Validation.areAllNotBlank(email)
                    && regex.matches(email!!)
        }
    }
}
