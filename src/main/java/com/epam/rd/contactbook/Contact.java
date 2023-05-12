package com.epam.rd.contactbook;

public class Contact {
    private ContactInfo [] contactInfo = new ContactInfo[1];
    private final ContactInfo [] nameContactInfo = new ContactInfo[1];
    private final ContactInfo [] phoneContactInfo = new ContactInfo[1];
    private final ContactInfo [] emailContactInfo = new ContactInfo[3];
    private final ContactInfo [] socialContactInfo = new ContactInfo[5];
    private String contactName;
    private int nameCount = 0;
    private int emailCount = 0;
    private int socialCount = 0;
    private int phoneCount = 0;

    public Contact(String contactName) {
        this.contactName = contactName;
        nameContactInfo[nameCount++] = this.new NameContactInfo();
        generateInfo();
    }

    private class NameContactInfo implements ContactInfo{
        @Override
        public String getTitle() {
            return "Name";
        }
        @Override
        public String getValue() {
            return contactName;
        }
    }

    public static class Email implements ContactInfo {
        String title;
        String value;
        public Email(String title, String value){
            this.title = title;
            this.value = value;
        }
        @Override
        public String getTitle() {
            return title;
        }
        @Override
        public String getValue() {
            return value;
        }
    }

    public static class Social implements ContactInfo {
        String title;
        String value;
        public Social (String title, String value){
            this.title = title;
            this.value = value;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getValue() {
            return value;
        }
    }


    public void rename(String newName) {
        if(newName != null && !newName.equals("")){
            contactName = newName;
        }
    }

    public Email addEmail(String localPart, String domain) {
        if (emailCount < 3){
            Email email = new Email("Email",localPart + "@" + domain);
            emailContactInfo[emailCount++] = email;
            generateInfo();

            return email;
        }
        return null;
    }

    public Email addEpamEmail(String firstname, String lastname) {
        if (emailCount < 3){
            Email mail = new Contact.Email(firstname,lastname){
                @Override
                public String getTitle(){
                    return "Epam Email";
                }
                @Override
                public String getValue(){
                    return firstname + '_' + lastname + "@epam.com";
                }
            };
            emailContactInfo[emailCount++] = mail;
            generateInfo();

            return mail;
        }
        return null;
    }

    public ContactInfo addPhoneNumber(int code, String number) {
        if (phoneCount < 1){
            ContactInfo phone = new ContactInfo(){
                public String getTitle(){
                    return "Tel";
                }
                public String getValue(){
                    return "+" + code + ' ' + number;
                }
            };
            phoneContactInfo[phoneCount++] = phone;
            generateInfo();

            return phone;
        }
        return null;
    }

    public Social addTwitter(String twitterId) {
        if (socialCount < 5){
            Social twitter = new Contact.Social("Twitter", twitterId);
            socialContactInfo[socialCount++] = twitter;
            generateInfo();

            return twitter;
        }
        return null;
    }

    public Social addInstagram(String instagramId) {
        if (socialCount < 5){
            Social instagram = new Contact.Social("Instagram", instagramId);
            socialContactInfo[socialCount++] = instagram;
            generateInfo();

            return instagram;
        }
        return null;
    }

    public Social addSocialMedia(String title, String id) {
        if (socialCount < 5){
            Social social = new Contact.Social(title, id);
            socialContactInfo[socialCount++] = social;
            generateInfo();

            return social;
        }
        return null;
    }

    public ContactInfo[] getInfo() {
        return contactInfo;
    }

    private void generateInfo (){
        ContactInfo[] temp;
        if (nameCount > 0) {
            contactInfo = new ContactInfo[nameCount];
            System.arraycopy(nameContactInfo, 0, contactInfo, 0, nameCount);
        }
        if (phoneCount > 0) {
            int contactLength = contactInfo.length;
            temp = contactInfo;
            contactInfo = new ContactInfo[phoneCount + contactLength];
            System.arraycopy(temp, 0, contactInfo, 0, temp.length);
            System.arraycopy(phoneContactInfo, 0, contactInfo, nameContactInfo.length, phoneCount);
        }
        if (emailCount > 0) {
            int contactLength = contactInfo.length;
            temp = contactInfo;
            contactInfo = new ContactInfo[emailCount + contactLength];
            System.arraycopy(temp, 0, contactInfo, 0, temp.length);
            System.arraycopy(emailContactInfo, 0, contactInfo, contactLength, emailCount);
        }
        if (socialCount > 0){
            int contactLength = contactInfo.length;
            temp = contactInfo;
            contactInfo = new ContactInfo[socialCount+contactLength];
            System.arraycopy(temp, 0, contactInfo, 0, temp.length);
            System.arraycopy(socialContactInfo, 0, contactInfo, contactLength, socialCount);
        }
    }

}
