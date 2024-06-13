const messages = {
    brand: 'SkillTree',
    toaster: 'SkillTree',
    save: 'Sauvegarder',
    datePatternPlaceHolder: 'jj/mm/aaaa',
    signIn: {
        title: 'Connexion',
        username: 'Nom d\'utilisateur',
        usernameHelp: 'Adresse email du compte',
        password: 'Mot de passe',
        submit: 'Se connecter'
    },
    admin: {
        navbar: {
            referentials: {
                dropdown: 'Référentiels',
                certifications: 'Titres professionnels',
                activities: 'Activités-types',
                skills: 'Compétences'
            },
            trainings: {
                dropdown: 'Formations',
                create: 'Nouvelle formation'
            },
            accounts: {
                dropdown: 'Comptes',
                create: 'Nouveau compte',
                list: 'Liste des comptes'
            }
        },
        home: {
            title: 'Tableau de bord administrateur'
        },
        referentials: {
            certifications: {
                createTitle: 'Créer un titre professionnel',
                level: 'Équivalence de diplôme',
                levelOption: 'Choisir un niveau',
                code: 'Code',
                codeHelp: 'Doit être unique',
                name: 'Nom',
                acronym: 'Sigle',
                startYear: 'Millésime',
                startYearHelp: 'Entre 2018 et l\'année en cours',
                description: 'Description',
                descriptionHelp: 'Maximum 5000 caractères',
                createSuccess: 'Titre professionnel créé avec succès.'
            }, activities: {
                createTitle: 'Créer une activité-type',
                certification: 'Titre professionnel',
                certificationHelp: 'Titre professionnel auquel associer l\'activité-type',
                certificationOption: 'Choisir un titre',
                code: 'Code',
                codeHelp: 'Doit être unique',
                name: 'Nom',
                description: 'Description',
                descriptionHelp: 'Maximum 5000 caractères',
                createSuccess: 'Activité-type créée avec succès.'
            }, skills: {
                createTitle: 'Créer une compétence professionnelle',
                certification: 'Titre professionnel',
                certificationHelp: 'Filtrer pour afficher la liste des activités-types du titre',
                certificationOption: 'Choisir un titre',
                activity: 'Activité-type',
                activityHelp: 'Activité-type à laquelle associer la compétence professionnelle',
                activityOption: 'Choisir une activité',
                code: 'Code',
                codeHelp: 'Doit être unique',
                name: 'Nom',
                description: 'Description',
                descriptionHelp: 'Maximum 5000 caractères',
                createSuccess: 'Compétence professionnelle créée avec succès.'
            }
        },
        trainings: {
            createTitle: 'Créer une formation',
            certification: 'Titre professionnel',
            certificationHelp: 'Titre professionnel auquel prépare la formation',
            certificationOption: 'Choisir un titre',
            name: 'Nom',
            nameHelp: 'Doit être unique',
            startDate: 'Date de début',
            endDate: 'Date de fin',
            endDateHelp: 'Doit être après à la date de début',
            description: 'Description',
            descriptionHelp: 'Maximum 2000 caractères',
            createSuccess: 'Formation créée avec succès.'
        },
        accounts: {
            createTitle: 'Créer un compte',
            listTitle: 'Liste des comptes',
            username: 'Adresse email du compte',
            usernameHelp: 'Doit être unique',
            firstname: 'Prénom',
            lastname: 'Nom',
            password: 'Mot de passe',
            createSuccess: 'Compte créé avec succès.',
            resetSuccess: 'Mot de passe modifié avec succès.'
        }
    }, trainer: {
        home: {
            title: 'Tableau de bord formateur'
        }
    },
    errors: {
        unhandledError: 'Oops, une erreur inconnue s\'est produite.',
        badRequest: {
            E_BAD_CREDENTIALS: 'Aucun compte ne correspond aux identifiants.',
            E_TRAINING_DATES_MUST_BE_CONSISTENT: 'La date de fin doit être après la date de début.'
        }
    }
};

export default messages;
