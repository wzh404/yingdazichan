var FormValidate ={
    createNew: function(){
        var validate = {};
        var mask = 0x00;
        var ins = $("input[data-validate]");
        var mm = ((0x01 << ins.length) - 1);

        validate.getPosition = function(id){
            for (var i = 0; i < ins.length; i++) {
                if (ins[i].id == id)
                    return i;
            }
            return -1;
        };

        validate.setMaskById = function(b, id){
            var inx = validate.getPosition(id);
            if (inx >= 0) {
                validate.setMask(b, 0x01 << inx);
            }
        };

        validate.setMask = function(r, v){
            if (r)
                mask |= v;
            else
                mask &= (mm - v);
        };

        validate.blur = function(){
            for (var i = 0; i < ins.length; i++) {
                if (ins[i].value != ''){
                    validate.setMaskById(true, ins[i].id);
                }

                console.log($('#span_errmsg_' + ins[i].id).length);
                if ($('#span_errmsg_' + ins[i].id).length < 1){
                    console.log('add error ' + ins[i].id);
                    var errMsg = '<span style="color:red;display:none" id="span_errmsg_' + ins[i].id + '"></span>';
                    $('#' + ins[i].id).parent().append(errMsg);
                }
                $('#' + ins[i].id).blur(function () {
                    var b = checkValue(this.id);
                    validate.setMaskById(b, this.id);
                });
            }
        };

        validate.check = function(){
            for (var i = 0; i < ins.length; i++) {
                var b = checkValue(ins[i].id);
                validate.setMaskById(b, ins[i].id);
            }
        }

        validate.submit = function(id){
            validate.check();
            console.log(mask + "-" + mm);
            if (mask != mm) {
                return;
            }

            $(id).submit();
        }

        return validate;
    }
}

function create_form_validate(){
    var v = FormValidate.createNew();
    v.blur();

    return v;
}