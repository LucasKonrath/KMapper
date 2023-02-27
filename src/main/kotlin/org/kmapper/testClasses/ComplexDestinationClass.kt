package org.kmapper.testClasses

data class ComplexDestinationClass(val intOne: Int, val stringOne: String, val doubleOne: Double, val shortOne: Short, val byteOne: Byte,
                                   val intTwo: Int, val stringTwo: String, val doubleTwo: Double, val shortTwo: Short, val byteTwo: Byte,
                                   val intThree: Int, val stringThree: String, val doubleThree: Double, val shortThree: Short, val byteThree: Byte,
                                   val intFour: Int, val stringFour: String, val doubleFour: Double, val shortFour: Short, val byteFour: Byte,
                                   val intFive: Int, val stringFive: String, val doubleFive: Double, val shortFive: Short, val byteFive: Byte,
                                   val int6: Int, val string6: String, val double6: Double, val short6: Short, val byte6: Byte,
                                   val int7: Int, val string7: String, val double7: Double, val short7: Short, val byte7: Byte,
                                   val int8: Int, val string8: String, val double8: Double, val short8: Short, val byte8: Byte,
                                   val int9: Int, val string9: String, val double9: Double, val short9: Short, val byte9: Byte,
                                   val int10: Int, val string10: String, val double10: Double, val short10: Short, val byte10: Byte,
                                   val int11: Int, val string11: String, val double11: Double, val short11: Short, val byte11: Byte,
                                   val int12: Int, val string12: String, val double12: Double, val short12: Short, val byte12: Byte
    ){
        constructor(complexClass: ComplexClass) : this(complexClass.intOne, complexClass.stringOne, complexClass.doubleOne, complexClass.shortOne, complexClass.byteOne,
            complexClass.intTwo, complexClass.stringTwo, complexClass.doubleTwo, complexClass.shortTwo, complexClass.byteTwo,
            complexClass.intThree, complexClass.stringThree, complexClass.doubleThree, complexClass.shortThree, complexClass.byteThree,
            complexClass.intFour, complexClass.stringFour, complexClass.doubleFour, complexClass.shortFour, complexClass.byteFour,
            complexClass.intFive, complexClass.stringFive, complexClass.doubleFive, complexClass.shortFive, complexClass.byteFive,
            complexClass.int6, complexClass.string6, complexClass.double6, complexClass.short6, complexClass.byte6,
            complexClass.int7, complexClass.string7, complexClass.double7, complexClass.short7, complexClass.byte7,
            complexClass.int8, complexClass.string8, complexClass.double8, complexClass.short8, complexClass.byte8,
            complexClass.int9, complexClass.string9, complexClass.double9, complexClass.short9, complexClass.byte9,
            complexClass.int10, complexClass.string10, complexClass.double10, complexClass.short10, complexClass.byte10,
            complexClass.int11, complexClass.string11, complexClass.double11, complexClass.short11, complexClass.byte11,
            complexClass.int12, complexClass.string12, complexClass.double12, complexClass.short12, complexClass.byte12) {
        }
    }