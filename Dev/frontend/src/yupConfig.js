import { setLocale } from "yup";

setLocale({
  string: {
    email: "Email deve ser válido",
    required: "Campo obrigatório",
  },
  mixed: {
    default: "Campo inválido",
    required: "Campo obrigatório",
    notType: "Inválido",
  },
  date: {
    max: ({ max }) => `Data deve ser menor que ${max}`,
    min: ({ min }) => `Data deve ser maior que ${min}`,
  },
});
