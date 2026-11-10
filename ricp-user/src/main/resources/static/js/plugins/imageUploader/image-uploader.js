/*! Image Uploader - v1.0.0 - 15/07/2019
 * Copyright (c) 2019 Christian Bayer; Licensed MIT */

(function ($) {

    $.fn.imageUploader = function (options) {

        // Default settings
        var defaults = {
            preloaded: [],
            imagesInputName: 'images',
            preloadedInputName: 'preloaded',
            label: 'Drag & Drop files here or click to browse'
        };

        // Get instance
        var plugin = this;

        // Set empty settings
        plugin.settings = {};

        // Plugin constructor
        plugin.init = function () {

            // Define settings
            plugin.settings = $.extend(plugin.settings, defaults, options);

            // Run through the elements
            plugin.each(function (i, wrapper) {

                // Create the container
                var $container = createContainer();

                // Append the container to the wrapper
                $(wrapper).append($container);

                // If there are preloaded images
                if (plugin.settings.preloaded.length) {

                    // Change style
                    $container.addClass('has-files');

                    // Get the upload images container
                    var $uploadedContainer = $container.find('.uploaded');

                    // Set preloaded images preview
                    for (var i = 0; i < plugin.settings.preloaded.length; i++) {
                        $uploadedContainer.append(createImg(plugin.settings.preloaded[i].src, plugin.settings.preloaded[i].id, true));
                    }

                }

            });

        };


        var dataTransfer = new DataTransfer();

        var createContainer = function () {

            // Create the image uploader container
            var $container = $('<div>', {class: 'image-uploader'}),

                // Create the input type file and append it to the container
                $input = $('<input>', {
                    type: 'file',
                    id: plugin.settings.imagesInputName + '-' + random(),
                    name: plugin.settings.imagesInputName + '[]',
                    multiple: ''
                }).appendTo($container),

                // Create the uploaded images container and append it to the container
                $uploadedContainer = $('<div>', {class: 'uploaded'}).appendTo($container),

                // Create the text container and append it to the container
                $textContainer = $('<div>', {
                    class: 'upload-text'
                }).appendTo($container),

                // Create the icon and append it to the text container
                $i = $('<i>', {class: 'material-icons', text: 'cloud_upload'}).appendTo($textContainer),

                // Create the text and append it to the text container
                $span = $('<span>', {text: plugin.settings.label}).appendTo($textContainer);


            // Listen to container click and trigger input file click
            $container.on('click', function (e) {
                // Prevent browser default event and stop propagation
                prevent(e);

                // Trigger input click
                $input.trigger('click');
            });

            // Stop propagation on input click
            $input.on("click", function (e) {
                e.stopPropagation();
            });

            // Listen to input files changed
            $input.on('change', fileSelectHandler.bind($container));

            return $container;
        };


        var prevent = function (e) {
            // Prevent browser default event and stop propagation
            e.preventDefault();
            e.stopPropagation();
        };

        var createImg = function (src, id) {

            // Create the upladed image container
            var $container = $('<div>', {class: 'uploaded-image'}),

                // Create the img tag
                $img = $('<img>', {src: src}).appendTo($container),

                // Create the delete button
                $button = $('<button>', {class: 'delete-image'}).appendTo($container),

                // Create the delete icon
                $i = $('<i>', {class: 'material-icons', text: 'clear'}).appendTo($button);

            // If the images are preloaded
            if (plugin.settings.preloaded.length) {

                // Set a identifier
                $container.attr('data-preloaded', true);

                // Create the preloaded input and append it to the container
                var $preloaded = $('<input>', {
                    type: 'hidden',
                    name: plugin.settings.preloadedInputName + '[]',
                    value: id
                }).appendTo($container)

            } else {

                // Set the identifier
                $container.attr('data-index', id);

            }

            // Stop propagation on click
            $container.on("click", function (e) {
                // Prevent browser default event and stop propagation
                prevent(e);
            });

            // Set delete action
            $button.on("click", function (e) {
                // Prevent browser default event and stop propagation
                prevent(e);

                // If is not a preloaded image
                if ($container.data('index')) {

                    // Get the image index
                    var index = parseInt($container.data('index'));

                    // Update other indexes
                    $container.find('.uploaded-image[data-index]').each(function (i, cont) {
                        if (i > index) {
                            $(cont).attr('data-index', i - 1);
                        }
                    });

                    // Remove the file from input
                    dataTransfer.items.remove(index);
                }

                // Remove this image from the container
                $container.remove();

                // If there is no more uploaded files
                if (!$container.find('.uploaded-image').length) {

                    // Remove the 'has-files' class
                    $container.removeClass('has-files');

                }

            });

            return $container;
        };

        var fileSelectHandler = function (e) {

            // Prevent browser default event and stop propagation
            prevent(e);

            // Get the jQuery element instance
            var $container = $(this);

            // Change the container style
            $container.removeClass('drag-over');

            // Get the files
            var files = e.target.files || e.originalEvent.dataTransfer.files;

            // Makes the upload
            setPreview($container, files);
        };

        var setPreview = function ($container, files) {
            // Add the 'has-files' class
            $container.addClass('has-files');

            // Get the upload images container
            var $uploadedContainer = $container.find('.uploaded'),
                // Get the files input
                $input = $container.find('input[type="file"]');

            // Run through the files
            $(files).each(function (i, file) {

                // Add it to data transfer
                dataTransfer.items.add(file);

                // Set preview
                $uploadedContainer.append(createImg(URL.createObjectURL(file), dataTransfer.items.length - 1));

            });

            // Update input files
            $input.prop('files', dataTransfer.files);

        };

        // Generate a random id
        var random = function () {
            return Date.now() + Math.floor((Math.random() * 100) + 1);
        };

        this.init();

        // Return the instance
        return this;
    };

}(jQuery));