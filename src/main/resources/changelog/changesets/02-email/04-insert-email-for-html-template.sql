--liquibase formatted sql
--changeset biju:1

INSERT INTO html_templates(
                          template,
                          template_name
) VALUES(
         '<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Verify Your Account</title>
</head>
<body style="font-family: ''Arial'', sans-serif; background-color: #ffffff; margin: 0; padding: 0;">

  <table width="100%" cellpadding="0" cellspacing="0" style="max-width: 600px; margin: auto; border-collapse: collapse;">
    <tr>
      <td align="center" style="padding: 40px 0;">
        <img src="https://i.imgur.com/LkVnD9s.png" alt="Shield Icon" width="100" />
      </td>
    </tr>

    <tr>
      <td align="center" style="color: #003366; font-size: 28px; font-weight: bold; padding-bottom: 10px;">
        Verify Your Account
      </td>
    </tr>

    <tr>
      <td align="center" style="font-size: 16px; color: #333; padding: 10px 20px;">
        Hello <strong>${firstname} <span> </span>${lastname}</strong>,
      </td>
    </tr>

    <tr>
      <td style="padding: 10px 30px; color: #333; font-size: 16px; line-height: 1.6;">
        To finalize your account setup with FinTech and unlock all features, we need to verify your identity.
        This ensures the highest level of security for your account and protects your personal information.
        <br><br>
        Click the link below to verify your email address.
      </td>
    </tr>

    <tr>
      <td align="center" style="padding: 30px;">
        <a href="${verification_link}"
           style="background-color: #2196F3; color: white; padding: 12px 24px; text-decoration: none;
           font-size: 16px; border-radius: 5px; display: inline-block;">
          Verify my email
        </a>
      </td>
    </tr>

  </table>

</body>
</html>
',
         'cashier_template'
        ),
        (
         '<!DOCTYPE html>
         <html lang="en">
         <head>
             <meta charset="UTF-8">
             <title>Supplier Notification</title>
         </head>
         <body style="margin: 0; padding: 0; font-family: Arial, sans-serif; background-color: #f4f6f8;">
             <div style="width: 100%; padding: 40px 0; display: flex; justify-content: center;">

                 <div style="max-width: 500px; width: 90%; background-color: #ffffff; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); padding: 30px;">

                     <h1 style="color: #1a73e8; font-size: 26px; text-align: center; margin-bottom: 30px;">
                         Supplier Notification
                     </h1>

                     <p style="color: #333333; font-size: 18px; margin-bottom: 20px;">
                         Hello <strong>${supplierName}</strong>,
                     </p>

                     <div style="background-color: #f1f5fb; border: 1px solid #d1e3ff; border-radius: 8px; padding: 20px; margin-bottom: 25px;">
                         <p style="margin: 0; color: #555555; font-size: 16px; line-height: 1.6;">
                             ${remarks}
                         </p>
                     </div>

                     <p style="color: #444444; font-size: 16px; line-height: 1.6; margin-bottom: 30px;">
                         Please contact us to send the product.
                     </p>

                     <p style="color: #1a73e8; font-weight: bold; font-size: 16px; margin: 0;">
                         Thank You
                     </p>

                 </div>

             </div>
         </body>
         </html>',
         'SUPPLIER_EMAIL_LOG');