export class StringUtils {

  // Mètode per capitalitzar la primera lletra d'una cadena de text
  static capitalizeFirstLetter(str: string): string {
    return str.charAt(0).toUpperCase() + str.slice(1);
  }
}
